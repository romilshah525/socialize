package com.showme.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.showme.beans.RequestResponse;
import com.showme.beans.User;

@Component
public class RequestResponseDB extends SocializeDB {

	@Autowired
	UserDB userDB;
	
	@Autowired
	MessageDB messageDB;

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

	public Integer follow(Integer userId, Integer requestId) throws ClassNotFoundException, SQLException {

		long millis = System.currentTimeMillis();
		Date date = new Date(millis);
		Time time = new Time(millis);
		dbConnect();
		String sql = "INSERT INTO requesttable (request_by, request_to , date, time ) Values(?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, userId);
		pstmt.setInt(2, requestId);
		pstmt.setDate(3, date);
		pstmt.setTime(4, time);
		pstmt.execute();
		
		sql = "select id from requesttable where request_by=? and request_to=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, userId);
		pstmt.setInt(2, requestId);
		ResultSet rst = pstmt.executeQuery();
		
		Integer id = 0;
		
		while(rst.next()) {
			id = rst.getInt("id");
		}
		
		dbClose();
		
		return id;
		
	}

	public List<RequestResponse> allFollowRequestByUser(Integer userId) throws ClassNotFoundException, SQLException {

		List<RequestResponse> requests = new ArrayList<RequestResponse>();
		dbConnect();
		String sql = "SELECT * FROM requesttable where request_by=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, userId);
		ResultSet rst = pstmt.executeQuery();
		while (rst.next()) {
			RequestResponse request = new RequestResponse();
			request.setId(rst.getInt("id"));
			request.setRequest(rst.getInt("request_by"));
			request.setResponse(rst.getInt("request_to"));
			request.setDate(rst.getDate("date"));
			request.setTime(rst.getTime("time"));
			requests.add(request);
		}
		dbClose();
		return requests;
	}

	public List<RequestResponse> allResponseForUsers(Integer userId) throws ClassNotFoundException, SQLException {
		List<RequestResponse> requests = new ArrayList<RequestResponse>();
		dbConnect();
		String sql = "SELECT * FROM requesttable where request_to=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, userId);
		ResultSet rst = pstmt.executeQuery();
		while (rst.next()) {
			RequestResponse request = new RequestResponse();
			request.setId(rst.getInt("id"));
			request.setRequest(rst.getInt("request_by"));
			request.setResponse(rst.getInt("request_to"));
			request.setDate(rst.getDate("date"));
			request.setTime(rst.getTime("time"));
			requests.add(request);
		}

		dbClose();
		return requests;
	}

	public void acceptFollowRequest(Integer userId, Integer friendUserId) throws ClassNotFoundException, SQLException, IOException {

		User friendUser = userDB.getUser(friendUserId);
		User user = userDB.getUser(userId);
		dbConnect();

		String sql = "INSERT INTO contact ( user_id , name , contact , email , contact_id ) VALUES ( ? , ? , ? , ? ,? )";

		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, userId);
		pstmt.setString(2, friendUser.getName());
		pstmt.setString(3, friendUser.getContact());
		pstmt.setString(4, friendUser.getEmail());
		pstmt.setInt(5, friendUserId);

		pstmt.executeUpdate();

		sql = "INSERT INTO contact ( user_id , name , contact , email , contact_id ) VALUES ( ? , ? , ? , ? ,? )";
		pstmt.setInt(1, friendUserId);
		pstmt.setString(2, user.getName());
		pstmt.setString(3, user.getContact());
		pstmt.setString(4, user.getEmail());
		pstmt.setInt(5, userId);

		pstmt.executeUpdate();
		dbConnect();
		sql = "select * from chatrelation where (id1=? AND id2=?) OR (id1=? AND id2=?)";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, userId);
		pstmt.setInt(2, friendUserId);
		pstmt.setInt(3, friendUserId);
		pstmt.setInt(4, userId);

		ResultSet rst = pstmt.executeQuery();
		String tablename = null;

		while (rst.next()) {
			tablename = rst.getString("tablename");
			break;
		}

		if (tablename == null) {
			insertTableName(userId, friendUserId);
			tablename = userDB.getConversationTableName(userId, friendUserId);
			messageDB.createMessageTable(userId, friendUserId, tablename);
		}

	}

	public void removeRequest(Integer requestId) throws ClassNotFoundException, SQLException {

		dbConnect();

		String sql = "DELETE from requesttable where id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, requestId);
		pstmt.executeUpdate();
		dbClose();

	}

	private void insertTableName(Integer user_id, Integer contact_id) throws ClassNotFoundException, SQLException {

		dbConnect();

		String sql = "INSERT into chatrelation (id1,id2,tablename) VALUES( ?, ?, ?)";

		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, user_id);
		pstmt.setInt(2, contact_id);
		pstmt.setString(3, "chat_" + user_id.toString() + "_" + contact_id.toString());

		pstmt.executeUpdate();

	}


	
}
