package com.showme.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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
public class NotificationController {

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
	
	@RequestMapping("/notifications")
	public String notifications(@RequestParam("userId") Integer userId,
			 						Model model) {
		
		
		try {
			User user = userService.getUserWithId(userId);
			List<User> homeFriendSuggestion = userService.homeFriendRecommendation(userId);
			List<Message> homeFriendsMessages = messageService.gethomeMessagesFromFriends(userId);
			List<Notification> notificationsForHome = notificationService.getNotificationForHome(userId);
			List<Notification> allNotifications = notificationService.getAllNotifications(userId);
			
			for (Notification notification : allNotifications) {
				System.out.println(notification.getId());
			}
			
			model.addAttribute("user", user);
			model.addAttribute("homeFriendSuggestion", homeFriendSuggestion);
			model.addAttribute("homeFriendsMessages",homeFriendsMessages);
			model.addAttribute("notificationsForHome", notificationsForHome);
			model.addAttribute("allNotifications", allNotifications);
			model.addAttribute("userService", userService);
			
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("userId", userId);
		
		return "views/notifications";
	}
	
	
}
