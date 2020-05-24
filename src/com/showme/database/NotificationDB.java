package com.showme.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Component;


import com.showme.beans.Notification;

@Component
public class NotificationDB extends SocializeDB{

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
	
	public void setNotification(Notification notification) throws ClassNotFoundException, SQLException {
		
		dbConnect();
		String sql = "INSERT INTO notification "
				+ "( notificationForUserId , notificationFromUserId , typeOfNotification, postId, likeId, commentId, requestId, groupId, statusId) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, notification.getNotificationForUserId());
		pstmt.setInt(2, notification.getNotificationFromUserId());
		pstmt.setString(3, notification.getTypeOfNotification());
		pstmt.setInt(4, notification.getPostId());
		pstmt.setInt(5, notification.getLikeId());
		pstmt.setInt(6, notification.getCommentId());
		pstmt.setInt(7, notification.getRequestId());
		pstmt.setInt(8, notification.getGroupId());
		pstmt.setInt(9, notification.getStatusId());
		pstmt.executeUpdate();
		
		
		dbClose();
	}
	
	public List<Notification> getNotificationsForHome(Integer userId) throws ClassNotFoundException, SQLException {
		
		dbConnect();
		
		List<Notification> notificationsForHome = new ArrayList<Notification>();
		
		String sql = "Select * from notification where notificationForUserId = ? ORDER BY id DESC limit 5";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, userId);
		
		ResultSet rst = pstmt.executeQuery();
		
		while(rst.next()) {
			
			Notification notification = new Notification();
			
			notification.setId(rst.getInt("id"));
			notification.setNotificationForUserId(rst.getInt("notificationForUserId"));
			notification.setNotificationFromUserId(rst.getInt("notificationFromUserId"));
			notification.setTypeOfNotification(rst.getString("typeOfNotification"));
			notification.setPostId(rst.getInt("postId"));
			notification.setLikeId(rst.getInt("likeId"));
			notification.setCommentId(rst.getInt("commentId"));
			notification.setRequestId(rst.getInt("requestId"));
			notification.setGroupId(rst.getInt("groupId"));
			notification.setStatusId(rst.getInt("statusId"));
			
			notificationsForHome.add(notification);
	
		}
		
		dbClose();
		return notificationsForHome;
	}

	public List<Notification> getAllNotifications(Integer userId) throws ClassNotFoundException, SQLException {
		dbConnect();
		
		List<Notification> getAllNotifications = new ArrayList<Notification>();
		
		String sql = "Select * from notification where notificationForUserId = ? ORDER BY id DESC";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, userId);
		
		ResultSet rst = pstmt.executeQuery();
		
		while(rst.next()) {
			
			Notification notification = new Notification();
			
			notification.setId(rst.getInt("id"));
			notification.setNotificationForUserId(rst.getInt("notificationForUserId"));
			notification.setNotificationFromUserId(rst.getInt("notificationFromUserId"));
			notification.setTypeOfNotification(rst.getString("typeOfNotification"));
			notification.setPostId(rst.getInt("postId"));
			notification.setLikeId(rst.getInt("likeId"));
			notification.setCommentId(rst.getInt("commentId"));
			notification.setRequestId(rst.getInt("requestId"));
			notification.setGroupId(rst.getInt("groupId"));
			notification.setStatusId(rst.getInt("statusId"));
			
			getAllNotifications.add(notification);
	
		}
		
		dbClose();
		return getAllNotifications;
	}

	public Notification getNotification(Integer notificationId) throws ClassNotFoundException, SQLException {
		dbConnect();
		
		String sql = "Select * from notification where id = ?";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, notificationId);
		
		ResultSet rst = pstmt.executeQuery();
		
		Notification notification = new Notification();
		
		
		while(rst.next()) {
			
			notification.setId(rst.getInt("id"));
			notification.setNotificationForUserId(rst.getInt("notificationForUserId"));
			notification.setNotificationFromUserId(rst.getInt("notificationFromUserId"));
			notification.setTypeOfNotification(rst.getString("typeOfNotification"));
			notification.setPostId(rst.getInt("postId"));
			notification.setLikeId(rst.getInt("likeId"));
			notification.setCommentId(rst.getInt("commentId"));
			notification.setRequestId(rst.getInt("requestId"));
			notification.setGroupId(rst.getInt("groupId"));
			notification.setStatusId(rst.getInt("statusId"));
	
		}
		
		dbClose();
		return notification;	
	}

	public void removeNotification(Integer notificationId) throws ClassNotFoundException, SQLException {
		
			dbConnect();

			String sql = "DELETE from notification where id=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, notificationId);
			pstmt.executeUpdate();
			dbClose();

	}
	
}
