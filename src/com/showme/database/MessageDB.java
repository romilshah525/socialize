package com.showme.database;

import java.io.ByteArrayOutputStream;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.showme.beans.ConversationTable;
import com.showme.beans.Message;
import com.showme.beans.User;
import com.showme.enums.MediaTypeEnum;
import com.showme.security.SecureData;
import com.showme.services.UserService;


@Component
public class MessageDB extends DB {

	private String hostingPassword = "AAAqtf44583";
	private String hostingUrl = "jdbc:mysql://node22899-socialize.ams1.jls.docktera.net/";

	private String localPassword = "";
	private String localUrl = "jdbc:mysql://localhost:3306/";

	@Autowired
	UserService userService;

	@Autowired
	SecureData secureData;

	// AAAqtf44583
	// node22899-socialize.ams1.jls.docktera.net
	// localhost:3306
	private String username = "root";
	private String password = localPassword;
	private String dbName = "whatsapp_msg_db";
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

	public List<Message> gethomeMessagesFromFriends(Integer userId)
			throws ClassNotFoundException, SQLException, IOException {

		List<ConversationTable> listOfConversationTablesName = userService.getAllConversationTablesName(userId);

		List<Message> listOfHomeMessagesFromFriends = new ArrayList<Message>();

		dbConnect();

		for (ConversationTable conversationTableName : listOfConversationTablesName) {

			dbConnect();
			String sql = "SELECT * FROM " + conversationTableName.getTableName() + " ORDER BY srno DESC LIMIT 1";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rst = pstmt.executeQuery();

			Message textMessage = new Message();

			while (rst.next()) {

				System.out.println(conversationTableName.getTableName());

				if (rst.getInt("receiver") == userId) {

					textMessage.setId(rst.getInt("srno"));
					textMessage.setSenderId(rst.getInt("sender"));
					textMessage.setMessage(secureData.decryptTheMessage(rst.getString("msg")));
					textMessage.setReceiverId(rst.getInt("receiver"));
					textMessage.setView(rst.getBoolean("view"));
					textMessage.setDate(rst.getDate("date"));
					textMessage.setTime(rst.getTime("time"));

					if (textMessage.getMessage().length() >= 50)
						textMessage.setMessage(textMessage.getMessage().substring(0, 40));

				} else
					textMessage = null;

				Integer conversationId = 0;
				if (conversationTableName.getId1() == userId)
					conversationId = conversationTableName.getId2();
				else
					conversationId = conversationTableName.getId1();

				System.out.println("-------");
				System.out.println(conversationId);
				Message mediaMessage = getLastMediaMessageForHome(userId, conversationId);

				if (textMessage != null || mediaMessage != null) {

					if (textMessage == null) {
						System.out.println("mediamessage: " + mediaMessage.getReceiverId());
						System.out.println("1 " + mediaMessage.getSenderId());
						listOfHomeMessagesFromFriends.add(mediaMessage);

					} else if (mediaMessage == null) {
						System.out.println("2 " + textMessage.getSenderId());
						listOfHomeMessagesFromFriends.add(textMessage);

					} else {
						List<Message> tempMessageList = new ArrayList<Message>();
						tempMessageList.add(textMessage);
						tempMessageList.add(mediaMessage);

						List<Message> messageList = tempMessageList.stream()
								.sorted(Comparator.comparing(Message::getTime).reversed()).collect(Collectors.toList());

						tempMessageList = messageList.stream().sorted(Comparator.comparing(Message::getDate).reversed())
								.collect(Collectors.toList());

						System.out.println("3 " + tempMessageList.get(0).getSenderId());
						listOfHomeMessagesFromFriends.add(tempMessageList.get(0));
					}
				}
			}
		}

		dbClose();

		List<Message> messageList = listOfHomeMessagesFromFriends.stream()
				.sorted(Comparator.comparing(Message::getTime).reversed()).collect(Collectors.toList());

		listOfHomeMessagesFromFriends = messageList.stream().sorted(Comparator.comparing(Message::getDate).reversed())
				.collect(Collectors.toList());

		if (listOfHomeMessagesFromFriends.size() > 3)
			return listOfHomeMessagesFromFriends.subList(0, 3);
		else
			return listOfHomeMessagesFromFriends;
	}

	private Message getLastMediaMessageForHome(Integer userId, Integer conversationId)
			throws SQLException, ClassNotFoundException, IOException {

		String sql = "SELECT * FROM media where (sender=? AND receiver=?) OR (sender=? AND receiver=?) ORDER BY id DESC LIMIT 1";

		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, userId);
		pstmt.setInt(2, conversationId);
		pstmt.setInt(3, conversationId);
		pstmt.setInt(4, userId);

		ResultSet rst = pstmt.executeQuery();

		Message mediaMessage = null;

		while (rst.next()) {

			if (rst.getInt("receiver") == userId) {
				mediaMessage = new Message();
				mediaMessage.setId(rst.getInt("id"));
				mediaMessage.setSenderId(rst.getInt("sender"));
				mediaMessage.setReceiverId(rst.getInt("receiver"));
				mediaMessage.setMediaType(rst.getString("type"));
				mediaMessage.setMediaFileName(rst.getString("filename"));

				Blob blob = rst.getBlob("document");

				InputStream inputStream = blob.getBinaryStream();
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				byte[] buffer = new byte[4096];
				int bytesRead = -1;

				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}

				byte[] imageBytes = outputStream.toByteArray();
				String base64Image = Base64.getEncoder().encodeToString(imageBytes);

				if (mediaMessage.getMediaType().equals(MediaTypeEnum.PICTURE.toString()))
					mediaMessage.setMediaPicture(base64Image);
				if (mediaMessage.getMediaType().equals(MediaTypeEnum.DOCUMENT.toString()))
					mediaMessage.setMediaDocument(base64Image);
				if (mediaMessage.getMediaType().equals(MediaTypeEnum.VIDEO.toString()))
					mediaMessage.setMediaVideo(base64Image);

				mediaMessage.setDate(rst.getDate("date"));
				mediaMessage.setTime(rst.getTime("time"));

			} else
				mediaMessage = null;

		}

		return mediaMessage;
	}

	public void createMessageTable(Integer userId, Integer friendUserId, String tablename)
			throws ClassNotFoundException, SQLException {

		dbConnect();

		String sql = "CREATE TABLE " + tablename + " (srno int NOT NULL AUTO_INCREMENT, " + "sender varchar(255), "
				+ "msg varchar(255), " + "receiver varchar(255), " + "view boolean, " + "date Date, " + "time Time ,"
				+ "PRIMARY KEY (srno))";

		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.execute();

		dbClose();
	}

	public List<User> getContactAndLastMessagesForChatScreen(Integer userId)
			throws SQLException, ClassNotFoundException, IOException {

		List<User> users = new ArrayList<User>();
		List<ConversationTable> listOfConversationTablesName = userService.getAllConversationTablesName(userId);

		for (ConversationTable conversationTableName : listOfConversationTablesName) {

			User user;
			List<Message> listOfHomeMessagesFromFriends = new ArrayList<Message>();

			if (conversationTableName.getId1() == userId)
				user = userService.getUserWithId(conversationTableName.getId2());
			else
				user = userService.getUserWithId(conversationTableName.getId1());

			dbConnect();

			String sql = "SELECT * FROM " + conversationTableName.getTableName() + " ORDER BY srno DESC LIMIT 1";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rst = pstmt.executeQuery();

			while (rst.next()) {

				Message textMessage = new Message();

				System.out.println(conversationTableName.getTableName());

				if (rst.getInt("receiver") == userId || rst.getInt("sender") == userId) {

					textMessage.setId(rst.getInt("srno"));
					textMessage.setSenderId(rst.getInt("sender"));
					textMessage.setMessage(secureData.decryptTheMessage(rst.getString("msg")));
					textMessage.setReceiverId(rst.getInt("receiver"));
					textMessage.setView(rst.getBoolean("view"));
					textMessage.setDate(rst.getDate("date"));
					textMessage.setTime(rst.getTime("time"));

					if (textMessage.getMessage().length() >= 50)
						textMessage.setMessage(textMessage.getMessage().substring(0, 40));

				} else
					textMessage = null;

				Integer conversationId = 0;
				if (conversationTableName.getId1() == userId)
					conversationId = conversationTableName.getId2();
				else
					conversationId = conversationTableName.getId1();

				System.out.println("-------");
				System.out.println(conversationId);
				Message mediaMessage = getLastMediaMessageForHome(userId, conversationId);

				if (textMessage != null || mediaMessage != null) {

					if (textMessage == null) {
						System.out.println("mediamessage: " + mediaMessage.getReceiverId());
						System.out.println("1 " + mediaMessage.getSenderId());
						listOfHomeMessagesFromFriends.add(mediaMessage);

					} else if (mediaMessage == null) {
						System.out.println("2 " + textMessage.getSenderId());
						listOfHomeMessagesFromFriends.add(textMessage);

					} else {
						List<Message> tempMessageList = new ArrayList<Message>();
						tempMessageList.add(textMessage);
						tempMessageList.add(mediaMessage);

						List<Message> messageList = tempMessageList.stream()
								.sorted(Comparator.comparing(Message::getTime).reversed()).collect(Collectors.toList());

						tempMessageList = messageList.stream().sorted(Comparator.comparing(Message::getDate).reversed())
								.collect(Collectors.toList());

						
						System.out.println("3 " + tempMessageList.get(0).getSenderId());
						
						listOfHomeMessagesFromFriends.add(tempMessageList.get(0));
					}
				}
			}

			if(listOfHomeMessagesFromFriends.size()!=0) {
			
				long millis=System.currentTimeMillis();  
		        Date date=new Date(millis);
				long diffInMillies = Math.abs(date.getTime() - listOfHomeMessagesFromFriends.get(0).getDate().getTime());
				Long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
				user.setLastConversation(diff);
			
			}else {
				user.setLastConversation(new Long("999999999"));
			}
			
			user.setMessages(listOfHomeMessagesFromFriends);
			
			users.add(user);
		}

		
		users = users.stream()
				  .sorted(Comparator.comparing(User::getLastConversation))
				  .collect(Collectors.toList());
		
		dbClose();

		return users;

	}

	public List<Message> getConversationBetween(Integer userId, Integer friendId) throws ClassNotFoundException, SQLException, IOException {
		
		
		List<Message> messages = new ArrayList<Message>();
		String tableName = userService.getConversationTableName(userId, friendId);
		
		String sql = "SELECT * FROM " + tableName + " ORDER BY date DESC , time DESC";
		System.out.println(sql);
		dbConnect();
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rst = pstmt.executeQuery();

		while(rst.next()) {
			Message textMessage = new Message();

			textMessage.setId(rst.getInt("srno"));
			textMessage.setSenderId(rst.getInt("sender"));
			textMessage.setMessage(secureData.decryptTheMessage(rst.getString("msg")));
			textMessage.setReceiverId(rst.getInt("receiver"));
			textMessage.setView(rst.getBoolean("view"));
			textMessage.setDate(rst.getDate("date"));
			textMessage.setTime(rst.getTime("time"));
			textMessage.setMediaType(null);
			
			messages.add(textMessage);
		}
		
		sql = "SELECT * FROM media where (sender=? AND receiver=?) OR (sender=? AND receiver=?) ORDER BY date DESC , time DESC";

		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, userId);
		pstmt.setInt(2, friendId);
		pstmt.setInt(3, friendId);
		pstmt.setInt(4, userId);

		rst = pstmt.executeQuery();

		while (rst.next()) {
			
			Message mediaMessage = new Message();
			mediaMessage.setId(rst.getInt("id"));
			mediaMessage.setSenderId(rst.getInt("sender"));
			mediaMessage.setReceiverId(rst.getInt("receiver"));
			mediaMessage.setMediaType(rst.getString("type"));
			mediaMessage.setMediaFileName(rst.getString("filename"));

			Blob blob = rst.getBlob("document");

			InputStream inputStream = blob.getBinaryStream();
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[4096];
			int bytesRead = -1;

			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}

			byte[] imageBytes = outputStream.toByteArray();
			String base64Image = Base64.getEncoder().encodeToString(imageBytes);

			if (mediaMessage.getMediaType().equals(MediaTypeEnum.PICTURE.toString()))
				mediaMessage.setMediaPicture(base64Image);
			if (mediaMessage.getMediaType().equals(MediaTypeEnum.DOCUMENT.toString()))
				mediaMessage.setMediaDocument(base64Image);
			if (mediaMessage.getMediaType().equals(MediaTypeEnum.VIDEO.toString()))
				mediaMessage.setMediaVideo(base64Image);

			mediaMessage.setDate(rst.getDate("date"));
			mediaMessage.setTime(rst.getTime("time"));

			messages.add(mediaMessage);
			
		}
		
		messages = messages.stream().sorted(Comparator.comparing(Message::getTime))
				.collect(Collectors.toList());

		messages = messages.stream().sorted(Comparator.comparing(Message::getDate))
				.collect(Collectors.toList());

		dbClose();
		return messages;
	}
	
	public void insertMessage(Integer user_id, String msg, Integer contact_id) throws ClassNotFoundException, SQLException {
		
		String encry = secureData.encryptTheMessage(msg);
		System.out.println("Original message was: "+msg);
		System.out.println("Encrypted Message is : "+encry);
		System.out.println("Decrypted Message is: "+secureData.decryptTheMessage(encry));
		System.out.println("key is: "+(user_id+contact_id));
		long millis=System.currentTimeMillis();  
        Date date=new Date(millis);
	
        System.out.println("message from use to contact is :"+ msg);
        
        Time time = new Time(millis);
		
        String tablename = userService.getConversationTableName(user_id, contact_id);
        
		dbConnect();
		
		String sql = "Insert into "+tablename+" ( sender, msg, receiver, view, date, time) VALUES(?,?,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		pstmt.setInt(1, user_id);
		pstmt.setString(2, encry);
		pstmt.setInt(3, contact_id);
		pstmt.setBoolean(4, true);
		pstmt.setDate(5, date);
		pstmt.setTime(6, time);
	
		pstmt.executeUpdate();
		
		 System.out.println("enc message from use to contact is :"+ secureData.encryptTheMessage(msg));
		
		dbClose();
		
	}

}
