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
import com.showme.services.UserService;

@Controller
public class ProfileController {

	@Autowired
	UserService userService;
	
	@Autowired
	MessageService messageService;
	
	@Autowired
	NotificationService notificationService;
	
	@RequestMapping("/profile")
	public String viewProfile(@RequestParam("userId") Integer userId,
								@RequestParam("profileId") Integer profileId,
								Model model) {
		
		try {
			User profileUser = userService.getUserForProfile(profileId);
			User user = userService.getUserWithId(userId);
			Boolean connection = userService.checkConnection(userId, profileId);
			List<Message> homeFriendsMessages = messageService.gethomeMessagesFromFriends(userId);
			List<Notification> notificationsForHome = notificationService.getNotificationForHome(userId);
			
			model.addAttribute("user", user);
			model.addAttribute("profileUser", profileUser);
			model.addAttribute("connection", connection);
			model.addAttribute("homeFriendsMessages",homeFriendsMessages);
			model.addAttribute("notificationsForHome", notificationsForHome);
			model.addAttribute("userService", userService);
			
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("userId", userId);
		model.addAttribute("profileId", profileId);
		
		return "views/profile";
	}
}
