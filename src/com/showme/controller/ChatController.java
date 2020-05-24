package com.showme.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.showme.beans.Message;
import com.showme.beans.Notification;
import com.showme.beans.User;
import com.showme.services.MessageService;
import com.showme.services.NotificationService;
import com.showme.services.PostService;
import com.showme.services.StatusService;
import com.showme.services.UserService;

@Controller
public class ChatController {

	@Autowired
	UserService userService;
	
	@Autowired
	PostService postService;
	
	@Autowired
	StatusService statusService;
	
	@Autowired
	MessageService messageService;
	
	@Autowired
	NotificationService notificationService;
	
	@RequestMapping("/viewChat")
	public String showChatScreen(@RequestParam("userId") Integer userId,
									@RequestParam("friendId") Integer friendId,
										Model model) {
		
		
		try {
			User user = userService.getUserWithId(userId);
			User friend = userService.getUserWithId(friendId);
			friend.setMessages(messageService.getConversationBetween(userId, friendId));
			List<Message> homeFriendsMessages = messageService.gethomeMessagesFromFriends(userId);
			List<User> chatScreenContactWithMessage =  messageService.getContactAndLastMessagesForChatScreen(userId);
			List<Notification> notificationsForHome = notificationService.getNotificationForHome(userId);
			
			for (User u : chatScreenContactWithMessage) {
				System.out.println(u.getMessages().size());
			}
			
			model.addAttribute("user", user);
			model.addAttribute("friend", friend);
			model.addAttribute("homeFriendsMessages",homeFriendsMessages);
			model.addAttribute("notificationsForHome", notificationsForHome);
			model.addAttribute("chatScreenContactWithMessage",chatScreenContactWithMessage);
			model.addAttribute("userService", userService);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("userId", userId);
		
		return "views/chatScreen";
	}
	
	
	@RequestMapping("/chat")
	public String showChat(@RequestParam("userId") Integer userId,
										Model model) {
		
		User friend = new User();
		try {
			List<User> chatScreenContactWithMessage =  messageService.getContactAndLastMessagesForChatScreen(userId);
			User user = userService.getUserWithId(userId);
			
			if(chatScreenContactWithMessage!=null) {
				friend = userService.getUserWithId(chatScreenContactWithMessage.get(0).getId());
				friend.setMessages(messageService.getConversationBetween(userId, chatScreenContactWithMessage.get(0).getId()));
			}
			/*
			List<Message> homeFriendsMessages = messageService.gethomeMessagesFromFriends(userId);
			List<Notification> notificationsForHome = notificationService.getNotificationForHome(userId);
			
			for (User u : chatScreenContactWithMessage) {
				System.out.println(u.getMessages().size());
			}
			
			model.addAttribute("user", user);
			model.addAttribute("friend", friend);
			model.addAttribute("homeFriendsMessages",homeFriendsMessages);
			model.addAttribute("notificationsForHome", notificationsForHome);
			model.addAttribute("chatScreenContactWithMessage",chatScreenContactWithMessage);
			model.addAttribute("userService", userService);*/
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("userId", userId);
		
		return "redirect:/viewChat?userId="+userId+"&friendId="+friend.getId();
	}
	
	@RequestMapping("/sendMessage")
	public String sendMessage(@RequestParam("userId") Integer userId,
								@RequestParam("friendId") Integer friendId,
									Model model,
										HttpServletRequest request) {
		
		String message = request.getParameter("message");
		System.out.println(message);
		if(message!=null) {
			try {
				messageService.saveMessage(userId, message, friendId);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return "redirect:/viewChat?userId="+userId+"&friendId="+friendId;
	}
	
}
