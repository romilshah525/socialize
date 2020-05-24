package com.showme.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.showme.beans.Message;
import com.showme.beans.Notification;
import com.showme.beans.Post;
import com.showme.beans.Status;
import com.showme.beans.User;
import com.showme.services.MessageService;
import com.showme.services.NotificationService;
import com.showme.services.PostService;
import com.showme.services.StatusService;
import com.showme.services.UserService;

@Controller
public class HomeController {

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
	
	@RequestMapping("/home")
	public String showHome( @RequestParam("userId") Integer userId,
							 @RequestParam("postId") Integer postId,
							  @RequestParam("prev") Integer prev,
							   @RequestParam("next") Integer next,
							  	 Model model) {
		
		try {
			User user = userService.getUserWithId(userId);
			List<Post> homePosts = postService.getHomePosts(userId);
			if(homePosts.size()>prev * 5 && homePosts.size()>next * 5)
				homePosts = homePosts.subList(prev * 5, next * 5);
			else if(homePosts.size()>prev * 5)
				homePosts = homePosts.subList(prev * 5, homePosts.size());
			List<Status> homeStatus = statusService.getFriendsStatusForHome(userId);
			List<User> homeFriendSuggestion = userService.homeFriendRecommendation(userId);
			List<Message> homeFriendsMessages = messageService.gethomeMessagesFromFriends(userId);
			List<Notification> notificationsForHome = notificationService.getNotificationForHome(userId);
			
			model.addAttribute("user", user);
			model.addAttribute("homePosts", homePosts);
			model.addAttribute("homeStatus", homeStatus);
			model.addAttribute("homeFriendSuggestion", homeFriendSuggestion);
			model.addAttribute("homeFriendsMessages",homeFriendsMessages);
			model.addAttribute("notificationsForHome", notificationsForHome);
			model.addAttribute("userService", userService);
		
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("userId", userId);
		model.addAttribute("postId", postId);
		model.addAttribute("prev", prev);
		model.addAttribute("next", next);
		
		return "views/home";
	}
}
