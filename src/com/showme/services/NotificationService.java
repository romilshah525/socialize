package com.showme.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.showme.beans.Notification;
import com.showme.database.NotificationDB;
import com.showme.enums.NotificationEnum;

@Component
public class NotificationService {

	@Autowired
	NotificationDB notificationDB;
	
	public List<Notification> getNotificationForHome(Integer userId) throws ClassNotFoundException, SQLException{
		return notificationDB.getNotificationsForHome(userId);
	}
	
	public void likeNotification(Integer notificationForUserId, Integer notificationFromUserId,  Integer postId, Integer likeId) throws ClassNotFoundException, SQLException {
		Notification notification = new Notification();
		
		notification.setNotificationForUserId(notificationForUserId);
		notification.setNotificationFromUserId(notificationFromUserId);
		notification.setTypeOfNotification( NotificationEnum.LIKE.toString() );
		notification.setPostId(postId);
		notification.setLikeId(likeId);
		
		notificationDB.setNotification(notification);
	}
	
	public void commentNotification(Integer notificationForUserId, Integer notificationFromUserId,  Integer postId, Integer commentId) throws ClassNotFoundException, SQLException {
		Notification notification = new Notification();
		
		notification.setNotificationForUserId(notificationForUserId);
		notification.setNotificationFromUserId(notificationFromUserId);
		notification.setTypeOfNotification( NotificationEnum.COMMENT.toString() );
		notification.setPostId(postId);
		notification.setCommentId(commentId);
		
		notificationDB.setNotification(notification);
	}
	
	public void requestNotification(Integer notificationForUserId, Integer notificationFromUserId , Integer requestId) throws ClassNotFoundException, SQLException {
		Notification notification = new Notification();
		
		notification.setNotificationForUserId(notificationForUserId);
		notification.setNotificationFromUserId(notificationFromUserId);
		notification.setTypeOfNotification( NotificationEnum.REQUEST.toString() );
		notification.setRequestId(requestId);
		
		notificationDB.setNotification(notification);
	}
	
	public void statusNotification(Integer notificationFromUserId , Integer statusId) throws ClassNotFoundException, SQLException {
		Notification notification = new Notification();
		
		notification.setNotificationFromUserId(notificationFromUserId);
		notification.setTypeOfNotification( NotificationEnum.STATUS.toString() );
		notification.setStatusId(statusId);
		
		notificationDB.setNotification(notification);
	}

	public List<Notification> getAllNotifications(Integer userId) throws ClassNotFoundException, SQLException {
		return notificationDB.getAllNotifications(userId);
	}

	public Notification getNotification(Integer notificationId) throws ClassNotFoundException, SQLException {
		return notificationDB.getNotification(notificationId);
	}

	public void removeNotification(Integer notificationId) throws ClassNotFoundException, SQLException {
		notificationDB.removeNotification(notificationId);
		
	}

	
	
}
