package com.showme.services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.showme.beans.Message;
import com.showme.beans.User;
import com.showme.database.MessageDB;

@Component
public class MessageService {

	@Autowired
	MessageDB messageDB;
	
	public List<Message> gethomeMessagesFromFriends(Integer userId) throws ClassNotFoundException, SQLException, IOException{
		return messageDB.gethomeMessagesFromFriends(userId);
	}

	public List<User> getContactAndLastMessagesForChatScreen(Integer userId) throws ClassNotFoundException, SQLException, IOException {
		return messageDB.getContactAndLastMessagesForChatScreen(userId);
	}

	public List<Message> getConversationBetween(Integer userId, Integer friendId) throws ClassNotFoundException, SQLException, IOException {
		return messageDB.getConversationBetween(userId, friendId);
	}
	
	public void saveMessage(Integer userId, String message, Integer friendId) throws ClassNotFoundException, SQLException {
		messageDB.insertMessage(userId, message, friendId);
	}
}
