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
import com.showme.beans.Status;
import com.showme.beans.User;
import com.showme.services.MessageService;
import com.showme.services.NotificationService;
import com.showme.services.StatusService;
import com.showme.services.UserService;

@Controller
public class StatusController {

	@Autowired
	StatusService statusService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	MessageService messageService;
	
	@Autowired
	NotificationService notificationService;
	
	@RequestMapping("/stories")
	public String allStories(@RequestParam("userId") Integer userId,
							 @RequestParam("statusId") Integer statusId,
							  @RequestParam("prev") Integer prev,
							   @RequestParam("next") Integer next,
							  	 Model model) {
		
		try {
			List<Status> getFriendsStatusForPageStories = statusService.getFriendsStatusForPageStories(userId);
			if(getFriendsStatusForPageStories.size()>prev * 6 && getFriendsStatusForPageStories.size()>next * 6)
				getFriendsStatusForPageStories = getFriendsStatusForPageStories.subList(prev * 6, next * 6);
			else if(getFriendsStatusForPageStories.size()>prev * 6)
				getFriendsStatusForPageStories = getFriendsStatusForPageStories.subList(prev * 6, getFriendsStatusForPageStories.size());
			User user = userService.getUserWithId(userId);
			List<Message> homeFriendsMessages = messageService.gethomeMessagesFromFriends(userId);
			List<Notification> notificationsForHome = notificationService.getNotificationForHome(userId);
			
			model.addAttribute("user", user);
			model.addAttribute("getFriendsStatusForPageStories", getFriendsStatusForPageStories);
			model.addAttribute("homeFriendsMessages",homeFriendsMessages);
			model.addAttribute("notificationsForHome", notificationsForHome);
			model.addAttribute("userService", userService);
	
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("userId", userId);
		model.addAttribute("statusId", statusId);
		model.addAttribute("prev", prev);
		model.addAttribute("next", next);
		
		return "views/allStories";
	}
	
	@RequestMapping("/seeStory")
	public String seeStory(@RequestParam("userId") Integer userId,
			 				@RequestParam("statusId") Integer statusId,
			 				 Model model) {
		
		User user;
		try {
			user = userService.getUserWithId(userId);
			List<Message> homeFriendsMessages = messageService.gethomeMessagesFromFriends(userId);
			List<Notification> notificationsForHome = notificationService.getNotificationForHome(userId);
			Status status = statusService.getStatusInformation(statusId);
			User statusOfUser = userService.getUserWithId(status.getUserId());
			
			model.addAttribute("status",status);
			model.addAttribute("statusOfUser",statusOfUser);
			
			model.addAttribute("user", user);
			model.addAttribute("homeFriendsMessages",homeFriendsMessages);
			model.addAttribute("notificationsForHome", notificationsForHome);
			model.addAttribute("userService", userService);
			
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("userId", userId);
		
		return "views/seeStory";
	}
	
	@RequestMapping("/uploadStatus")
	public String uploadStatus(@RequestParam("userId") Integer userId,
								Model model,
									HttpServletRequest request) {
		
		String statusUrl = request.getParameter("data_url");
		String statusName = request.getParameter("status_selector");
		try {
			statusService.insertStatus(userId, statusUrl, statusName);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/stories?userId="+userId+"&statusId=0&prev=0&next=1";
	}
}
