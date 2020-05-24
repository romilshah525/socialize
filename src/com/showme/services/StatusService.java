package com.showme.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.showme.beans.Status;
import com.showme.database.StatusDB;

@Component
public class StatusService {

	@Autowired
	StatusDB statusDB;
	
	@Autowired
	NotificationService notificationService;
	
	public void insertStatus(Integer userId, String statusUrl, String statusName) throws ClassNotFoundException, SQLException, IOException {
		
		File pathFile = new File(statusName);
		
		FileOutputStream fos = new FileOutputStream(pathFile);
		
		String b64 = statusUrl.substring(statusUrl.indexOf(",") + 1);
		
	    byte[] decoder = Base64.getDecoder().decode(b64);
		
	    fos.write(decoder);
	    
	    fos.close();
	    
		statusDB.insertStatus(userId, pathFile.getPath());
		
		//notificationService.statusNotification( userId, statusId);
	}
	
	public List<Status> getStatusOfUser(Integer userId) throws ClassNotFoundException, SQLException, IOException{
		return statusDB.getStatusOfUser(userId);
	}
	
	public List<Status> getFriendsStatusForHome(Integer userId) throws ClassNotFoundException, SQLException, IOException{
		return statusDB.getFriendsStatusForHome(userId);
	}
	
	public Status getStatusInformation(Integer statusId) throws ClassNotFoundException, SQLException, IOException {
		return statusDB.getStatusInformation(statusId);
	}

	public List<Status> getFriendsStatusForPageStories(Integer userId) throws ClassNotFoundException, SQLException, IOException {
		return statusDB.getFriendsStatusForPageStories(userId);
	}
}
