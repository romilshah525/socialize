package com.showme.services;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.showme.beans.Notification;
import com.showme.database.RequestResponseDB;

@Component
public class RequestResponseService {

	@Autowired
	RequestResponseDB requestResponseDB;
	
	@Autowired
	NotificationService notificationService;
	
	public void followRequest(Integer userId, Integer friendId) throws ClassNotFoundException, SQLException {
		Integer requestId = requestResponseDB.follow(userId, friendId);
		notificationService.requestNotification(friendId, userId, requestId);
	}

	public void acceptFollowRequest(Integer userId, Integer notificationId) throws ClassNotFoundException, SQLException, IOException {
		
		Notification notification = notificationService.getNotification(notificationId);
		requestResponseDB.acceptFollowRequest(notification.getNotificationForUserId(), notification.getNotificationFromUserId());
		requestResponseDB.removeRequest(notification.getRequestId());
		notificationService.removeNotification(notificationId);
	}

	public void rejectFollowRequest(Integer userId, Integer notificationId) throws ClassNotFoundException, SQLException {
		
		Notification notification = notificationService.getNotification(notificationId);
		requestResponseDB.removeRequest(notification.getRequestId());
		notificationService.removeNotification(notificationId);
	}

}
