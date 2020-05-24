package com.showme.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.showme.beans.Message;
import com.showme.beans.Notification;
import com.showme.beans.Post;
import com.showme.beans.User;
import com.showme.services.MessageService;
import com.showme.services.NotificationService;
import com.showme.services.PostService;
import com.showme.services.UserService;

@Controller
public class ExploreController {

	@Autowired
	UserService userService;
	
	@Autowired
	MessageService messageService;
	
	@Autowired
	NotificationService notificationService;
	
	@Autowired
	PostService postService;
	
	@RequestMapping("/exploreSection")
	public String showExploreSection(@RequestParam("userId") Integer userId,
										Model model) {
		
		try {
			
			User user = userService.getUserWithId(userId);
			List<User> recommendedFriends = userService.exploreFriendRecommendation(userId);
			//List<User> exploreFriends = userService.getOtherUsersWhichAreNotAFriend(userId);
			List<Message> homeFriendsMessages = messageService.gethomeMessagesFromFriends(userId);
			List<Notification> notificationsForHome = notificationService.getNotificationForHome(userId);
			List<Post> randomPicturesOfUserWhichAreNotFriend = postService.getRandomPicturesOfUserWhichAreNotFriend(userId);
			
			model.addAttribute("user", user);
			model.addAttribute("recommendedFriends", recommendedFriends);
			//model.addAttribute("exploreFriends", exploreFriends);
			model.addAttribute("homeFriendsMessages",homeFriendsMessages);
			model.addAttribute("notificationsForHome", notificationsForHome);
			model.addAttribute("randomPicturesOfUserWhichAreNotFriend", randomPicturesOfUserWhichAreNotFriend);
			model.addAttribute("userService", userService);
			
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("userId", userId);
		
		return "views/exploreSection";
	}
	
	@RequestMapping("/exploreFriends")
	public String exploreFriends(@RequestParam("userId") Integer userId,
										Model model) {
		
		try {
			
			User user = userService.getUserWithId(userId);
			List<User> exploreFriends = userService.exploreFriends(userId);
			List<Message> homeFriendsMessages = messageService.gethomeMessagesFromFriends(userId);
			List<Notification> notificationsForHome = notificationService.getNotificationForHome(userId);
			
			model.addAttribute("user", user);
			model.addAttribute("exploreFriends", exploreFriends);
			model.addAttribute("homeFriendsMessages",homeFriendsMessages);
			model.addAttribute("notificationsForHome", notificationsForHome);
			model.addAttribute("userService", userService);
			
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("userId", userId);
		
		return "views/exploreFriends";
	}
	
	
}
