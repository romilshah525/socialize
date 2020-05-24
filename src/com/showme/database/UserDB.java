package com.showme.database;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.stereotype.Component;

import com.showme.beans.ConversationTable;
import com.showme.beans.User;

@Component
public class UserDB extends SocializeDB {

	private String hostingPassword = "AAAqtf44583";
	private String hostingUrl = "jdbc:mysql://node22899-socialize.ams1.jls.docktera.net/";
	
	private String localPassword = "";
	private String localUrl = "jdbc:mysql://localhost:3306/";
	
	//AAAqtf44583
	//node22899-socialize.ams1.jls.docktera.net
	//localhost:3306
	private String username = "root";
	private String password = localPassword;
	private String dbName = "whatsapp";
	private String url = localUrl + dbName;
	private String driver = "com.mysql.jdbc.Driver";
	private Connection con;


	private void dbClose() throws SQLException {
		con.close();
	}

	private void dbConnect() throws ClassNotFoundException, SQLException {
		// Step 2: load the driver
		Class.forName(driver);
		// Step 3: Make the connection
		con = DriverManager.getConnection(url, username, password);

	}

	public String returnNameOfUser(Integer userId) throws ClassNotFoundException, SQLException, IOException {
		// get User with all required Information

		dbConnect();

		String sql = "select name from user where id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, userId);

		ResultSet rst = pstmt.executeQuery();
		String name = null;
		while (rst.next()) {
			name = rst.getString("name");
		}

		dbClose();
		return name;
	}
	
	public User getUser(Integer userId) throws ClassNotFoundException, SQLException, IOException {
		// get User with all required Information

		dbConnect();

		User user = new User();
		String sql = "select * from user where id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, userId);

		ResultSet rst = pstmt.executeQuery();

		while (rst.next()) {

			user.setId(rst.getInt("id"));
			user.setName(rst.getString("name"));
			user.setEmail(rst.getString("email"));
			user.setContact(rst.getString("contact"));
			Blob blob = rst.getBlob("profile_img");
			InputStream inputStream = blob.getBinaryStream();
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[4096];
			int bytesRead = -1;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}
			byte[] imageBytes = outputStream.toByteArray();
			user.setProfileImage(Base64.getEncoder().encodeToString(imageBytes));
		}

		dbClose();
		return user;
	}
	
	public String getProfileImageOfUser(Integer userId) throws ClassNotFoundException, SQLException, IOException {

		dbConnect();
		String base64Image = null;
		String sql = "select * from user where id=?";

		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, userId);

		ResultSet rst = pstmt.executeQuery();

		while (rst.next()) {

			Blob blob = rst.getBlob("profile_img");

			InputStream inputStream = blob.getBinaryStream();
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[4096];
			int bytesRead = -1;

			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}

			byte[] imageBytes = outputStream.toByteArray();
			base64Image = Base64.getEncoder().encodeToString(imageBytes);

		}
		return base64Image;
	}

	public List<User> getFriendsOfUser(Integer userId) throws ClassNotFoundException, IOException, SQLException{
		
		List<User> users = new ArrayList<User>();
		String sql = "SELECT * FROM user WHERE id IN ( SELECT contact_id FROM contact WHERE user_id = ? )";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, userId);
		ResultSet rst = pstmt.executeQuery();
		
		while (rst.next()) {

			User user = new User();
			user.setId(rst.getInt("id"));
			user.setName(rst.getString("name"));
			user.setEmail(rst.getString("email"));
			user.setContact(rst.getString("contact"));

			Blob blob = rst.getBlob("profile_img");

			InputStream inputStream = blob.getBinaryStream();
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[4096];
			int bytesRead = -1;

			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}

			byte[] imageBytes = outputStream.toByteArray();
			
			user.setProfileImage(Base64.getEncoder().encodeToString(imageBytes));
		
			users.add(user);
		}
		
		dbClose();
		
		return users;
	}
	
	public List<Integer> getUserFriendsIds(Integer userId) throws SQLException, ClassNotFoundException {
		
		dbConnect();
		
		List<Integer> usersId = new ArrayList<Integer>();
		
		String sql = "SELECT id FROM user WHERE id IN ( SELECT contact_id FROM contact WHERE user_id = ? )";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, userId);
		ResultSet rst = pstmt.executeQuery();
		
		while (rst.next()) {
			usersId.add(rst.getInt("id"));
		}
		
		dbClose();
		
		return usersId;
	}
	
	public List<User> getInterestOfUsersOtherThanFriends(Integer userId) throws ClassNotFoundException, SQLException, IOException {
		
		dbConnect();
		
		List<User> users = new ArrayList<User>();
		
		String sql = "SELECT * " + 
					 "FROM user_interest" + 
					 " WHERE user_id NOT" + 
					 " IN (" +  
					 	"SELECT contact_id" + 
					 	" FROM contact" + 
					 	" WHERE user_id =?" + 
					 ")"+ 
					 " AND " + 
					 " user_id NOT" + 
					 " IN (" +  
					 	"SELECT request_to" + 
					 	" FROM requesttable" + 
					 	" WHERE request_by=?" + 
					 ")";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, userId);
		pstmt.setInt(2, userId);
		
		ResultSet rst = pstmt.executeQuery();
		
		while(rst.next()) {
			
			User user = new User();
			user.setId(rst.getInt("user_id"));
			List<Integer> interest = new ArrayList<Integer>();
			for (int i = 0; i < 10; i++) {
				interest.add(rst.getInt(3+i));
			}
			user.setUserInterest(interest);		
			
			users.add(user);
		}
	
		dbClose();
		
		return users;
	}
	
	public User getInterestOfUser(Integer userId) throws ClassNotFoundException, SQLException{
		 
		User user = new User();
		dbConnect();
		
		String sql = "SELECT *" + 
				"	FROM user_interest " + 
				"	WHERE user_id =?";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, userId);
		
		ResultSet rst = pstmt.executeQuery();
		
		while(rst.next()) {
		
			user.setId(rst.getInt("user_id"));
			
			List<Integer> interest = new ArrayList<Integer>();
			for (int i = 0; i < 10; i++) {
				interest.add(rst.getInt(3+i));
				
			}
			
			user.setUserInterest(interest);	
		}
		
		dbClose();
		
		return user;
	}
	
	public List<ConversationTable> getAllConversationTablesName(Integer userId) throws ClassNotFoundException, SQLException {
	
	
		List<ConversationTable> listOfTablesName = new ArrayList<ConversationTable>();

		dbConnect();

		String sql = "SELECT * FROM chatrelation WHERE id1 = ? OR id2 = ? ";

		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, userId);
		pstmt.setInt(2, userId);

		ResultSet rst = pstmt.executeQuery();

		while (rst.next()) {
			ConversationTable conTable = new ConversationTable();
			conTable.setId1(rst.getInt("id1"));
			conTable.setId2(rst.getInt("id2"));
			conTable.setTableName(rst.getString("tablename"));
			listOfTablesName.add(conTable);
		}

		dbClose();
		return listOfTablesName;
	}
	
	public String getConversationTableName(Integer userId, Integer FriendId) throws ClassNotFoundException, SQLException {
		
		String tablename = null;

		dbConnect();

		String sql = "select tablename from chatrelation where (id1=? AND id2=?) OR (id1=? AND id2=?)";

		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, userId);
		pstmt.setInt(2, FriendId);
		pstmt.setInt(3, FriendId);
		pstmt.setInt(4, userId);

		ResultSet rst = pstmt.executeQuery();

		while (rst.next()) {
			tablename = rst.getString("tablename");
			break;
		}

		return tablename;
	}

	public List<User> getOtherUsersWhichAreNotAFriend(Integer userId) throws SQLException, IOException, ClassNotFoundException {
		
		dbConnect();
		
		List<User> users = new ArrayList<User>();
		
		String sql = "SELECT * FROM user WHERE id Not IN ( SELECT contact_id FROM contact WHERE user_id = ? )";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, userId);
		ResultSet rst = pstmt.executeQuery();
		
		while (rst.next()) {

			User user = new User();
			user.setId(rst.getInt("id"));
			user.setName(rst.getString("name"));
			user.setEmail(rst.getString("email"));
			user.setContact(rst.getString("contact"));

			Blob blob = rst.getBlob("profile_img");

			InputStream inputStream = blob.getBinaryStream();
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[4096];
			int bytesRead = -1;

			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}

			byte[] imageBytes = outputStream.toByteArray();
			
			user.setProfileImage(Base64.getEncoder().encodeToString(imageBytes));
		
			users.add(user);
		}
		
		dbClose();
		
		return users;
		
	}

	public List<Integer> getUserFriendsIdsAndFollowings(Integer userId) throws ClassNotFoundException, SQLException {
		dbConnect();
		
		List<Integer> usersId = new ArrayList<Integer>();
		
		String sql = "SELECT id FROM user WHERE id IN ( SELECT contact_id FROM contact WHERE user_id = ? )";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, userId);
		ResultSet rst = pstmt.executeQuery();
		
		while (rst.next()) {
			usersId.add(rst.getInt("id"));
		}
		
		sql = "SELECT id FROM WHERE id IN ( SELECT request_to FROM requesttable WHERE request_by = ? )";
		
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, userId);
		rst = pstmt.executeQuery();
		
		while (rst.next()) {
			usersId.add(rst.getInt("id"));
		}
		
		dbClose();
		
		return usersId;
	}

	public boolean checkConnection(Integer userId, Integer anotherUserId) throws ClassNotFoundException, SQLException {
		dbConnect();
		
		Integer id = 0;
		
		String sql = "SELECT * FROM contact WHERE ( user_id = ? and contact_id=?) or ( user_id = ? and contact_id=? )";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, userId);
		pstmt.setInt(2, anotherUserId);
		pstmt.setInt(3, anotherUserId);
		pstmt.setInt(4, userId);
		ResultSet rst = pstmt.executeQuery();
		
		while (rst.next()) {
			id = rst.getInt("contact_id");
		}
		
		if(id==0) {
			
			sql = "SELECT id FROM requesttable WHERE ( request_by = ? and request_to=? ) or ( request_by = ? and request_to=? )";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, userId);
			pstmt.setInt(2, anotherUserId);
			pstmt.setInt(3, anotherUserId);
			pstmt.setInt(4, userId);
			rst = pstmt.executeQuery();
			
			while (rst.next()) {
				id = rst.getInt("id");
			}
			
			if(id==0) {
				dbClose();
				return false;
			}
			else {
				dbClose();
				return true;
			}
			
		}
		else {
			
			dbClose();
			return true;
		}
	
	}

	public List<User> exploreFriends(Integer userId) throws ClassNotFoundException, SQLException, IOException {
		dbConnect();
		
		List<User> users = new ArrayList<User>();
		
		String sql = "SELECT * " + 
				 "FROM user" + 
				 " WHERE id NOT" + 
				 " IN (" +  
				 	"SELECT contact_id" + 
				 	" FROM contact" + 
				 	" WHERE user_id =?" + 
				 ")"+ 
				 " AND " + 
				 " id NOT" + 
				 " IN (" +  
				 	"SELECT request_to" + 
				 	" FROM requesttable" + 
				 	" WHERE request_by=?" + 
				 ") order by rand() limit 10";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, userId);
		pstmt.setInt(2, userId);
		ResultSet rst = pstmt.executeQuery();
		
		while (rst.next()) {

			User user = new User();
			user.setId(rst.getInt("id"));
			user.setName(rst.getString("name"));
			user.setEmail(rst.getString("email"));
			user.setContact(rst.getString("contact"));

			Blob blob = rst.getBlob("profile_img");

			InputStream inputStream = blob.getBinaryStream();
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[4096];
			int bytesRead = -1;

			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}

			byte[] imageBytes = outputStream.toByteArray();
			
			user.setProfileImage(Base64.getEncoder().encodeToString(imageBytes));
		
			user.setFriendsId( getUserFriendsIds(user.getId()) );		
			
			users.add(user);
		}
		
		dbClose();
		
		return users;
	}
}
