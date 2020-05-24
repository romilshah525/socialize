package com.showme.database;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.showme.beans.Status;

@Component
public class StatusDB extends SocializeDB {

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
	
	public List<Status> getStatusOfUser(Integer userId) throws ClassNotFoundException, SQLException, IOException {

		List<Status> statusOfUser = new ArrayList<Status>();

		dbConnect();

		String sql = "SELECT * FROM status WHERE user_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, userId);
		ResultSet rst = pstmt.executeQuery();

		while (rst.next()) {

			Status status = new Status();

			status.setId(rst.getInt("id"));
			status.setUserId(rst.getInt("user_id"));
			Blob blob = rst.getBlob("image");

			InputStream inputStream = blob.getBinaryStream();
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[4096];
			int bytesRead = -1;

			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}

			byte[] imageBytes = outputStream.toByteArray();
			String base64Image = Base64.getEncoder().encodeToString(imageBytes);

			status.setStatus(base64Image);

			status.setDate(rst.getDate("date"));
			status.setTime(rst.getTime("time"));

			statusOfUser.add(status);

		}

		List<Status> updatedStatus = new ArrayList<Status>();

		for (Status status : statusOfUser) {

			long millis = System.currentTimeMillis();
			Date date = new Date(millis);

			Time time = new Time(millis);
			
			long diffInMillies = Math.abs(date.getTime() - status.getDate().getTime());
			Long diff = TimeUnit.HOURS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		
			if ( diff > 24) {

				deleteStatusByStatusId(status.getId());

			} else {
				updatedStatus.add(status);
			}
		}

		dbClose();

		return updatedStatus;
	}

	public void deleteStatusByStatusId(Integer id) throws ClassNotFoundException, SQLException {

		dbConnect();

		String sql = "DELETE FROM status WHERE id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, id);

		pstmt.executeUpdate();

		dbClose();

	}
	
	public List<Status> getFriendsStatusForHome(Integer userId) throws SQLException, IOException, ClassNotFoundException{
		
		dbConnect();
		
		List<Status> statusForHome = new ArrayList<Status>();
		
		String sql = "SELECT * FROM status WHERE user_id IN ( SELECT contact_id FROM contact WHERE user_id =? ) OR user_id =? ORDER BY DATE DESC , TIME DESC LIMIT 3 ";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, userId);
		pstmt.setInt(2, userId);
		ResultSet rst = pstmt.executeQuery();

		System.out.println(rst);
		
		while (rst.next()) {

			Status status = new Status();

			status.setId(rst.getInt("id"));
			status.setUserId(rst.getInt("user_id"));
			Blob blob = rst.getBlob("image");

			InputStream inputStream = blob.getBinaryStream();
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[4096];
			int bytesRead = -1;

			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}

			byte[] imageBytes = outputStream.toByteArray();
			String base64Image = Base64.getEncoder().encodeToString(imageBytes);

			status.setStatus(base64Image);

			status.setDate(rst.getDate("date"));
			status.setTime(rst.getTime("time"));

			statusForHome.add(status);
			
			System.out.println(status.getId());
		}
		
		dbClose();
		
		return statusForHome;
	}

	public Status getStatusInformation(Integer statusId) throws ClassNotFoundException, SQLException, IOException {
		
		dbConnect();
		
		String sql = "SELECT * FROM status WHERE id = ? ";
		
		Status status = new Status();

		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, statusId);
	
		ResultSet rst = pstmt.executeQuery();

		while (rst.next()) {

			status.setId(rst.getInt("id"));
			status.setUserId(rst.getInt("user_id"));
			Blob blob = rst.getBlob("image");

			InputStream inputStream = blob.getBinaryStream();
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[4096];
			int bytesRead = -1;

			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}

			byte[] imageBytes = outputStream.toByteArray();
			String base64Image = Base64.getEncoder().encodeToString(imageBytes);

			status.setStatus(base64Image);

			status.setDate(rst.getDate("date"));
			status.setTime(rst.getTime("time"));

		}
		
		dbClose();
		
		return status;
	}
	
	public Integer insertStatus(Integer userId, String image)
			throws ClassNotFoundException, SQLException, FileNotFoundException {

		dbConnect();

		Status status = createStatus(userId, image);

		File file = new File(status.getStatus());
		
		FileInputStream fis = new FileInputStream( file );

		String sql = "INSERT INTO status ( user_id , image , date , time ) VALUES (  ? , ? , ? , ? )";

		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, status.getUserId());
		pstmt.setBinaryStream(2, fis, (int) file.length());
		pstmt.setDate(3, status.getDate());
		pstmt.setTime(4, status.getTime());

		pstmt.executeUpdate();

		String query = "select id from statusDB where user_id = ? and date=? and time=?";
		pstmt = con.prepareStatement(query);
		pstmt.setInt(1, userId);
		pstmt.setDate(3, status.getDate());
		pstmt.setTime(4, status.getTime());
		
		ResultSet rst = pstmt.executeQuery();
		
		Integer statusId = 0;
		
		while(rst.next()) {
			statusId = rst.getInt("id");
		}
		
		dbClose();
		
		return statusId;
	}

	private Status createStatus(Integer user_id, String image) {

		Status status = new Status();
		
		long millis = System.currentTimeMillis();
		Date date = new Date(millis);

		Time time = new Time(millis);

		status.setUserId(user_id);
		status.setStatus(image);
		status.setDate(date);
		status.setTime(time);

		return status;

	}

	public List<Status> getFriendsStatusForPageStories(Integer userId) throws ClassNotFoundException, SQLException, IOException {
		
		dbConnect();
		
		List<Status> statusForHome = new ArrayList<Status>();
		
		String sql = "SELECT * FROM status WHERE user_id IN ( SELECT contact_id FROM contact WHERE user_id =? ) OR user_id =? ORDER BY DATE DESC , TIME DESC";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, userId);
		pstmt.setInt(2, userId);
		ResultSet rst = pstmt.executeQuery();

		System.out.println(rst);
		
		while (rst.next()) {

			Status status = new Status();

			status.setId(rst.getInt("id"));
			status.setUserId(rst.getInt("user_id"));
			Blob blob = rst.getBlob("image");

			InputStream inputStream = blob.getBinaryStream();
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[4096];
			int bytesRead = -1;

			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}

			byte[] imageBytes = outputStream.toByteArray();
			String base64Image = Base64.getEncoder().encodeToString(imageBytes);

			status.setStatus(base64Image);

			status.setDate(rst.getDate("date"));
			status.setTime(rst.getTime("time"));

			statusForHome.add(status);
			
			
			System.out.println(status.getId());
		}
		
		dbClose();
		
		return statusForHome;
	}

}
