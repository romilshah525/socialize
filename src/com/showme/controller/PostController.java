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
import com.showme.beans.Post;
import com.showme.beans.User;
import com.showme.services.MessageService;
import com.showme.services.NotificationService;
import com.showme.services.PostService;
import com.showme.services.UserService;

@Controller
public class PostController {

	@Autowired
	PostService postService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	NotificationService notificationService;
	
	@Autowired
	MessageService messageService;
	
	@RequestMapping("/likePost")
	public String likePost( @RequestParam("userId") Integer userId,
							 @RequestParam("postId") Integer postId,
							  @RequestParam("prev") Integer prev,
							   @RequestParam("next") Integer next,
							  	 Model model ) {
		
		try {
			postService.likePost(userId, postId);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/home?userId="+userId+"&postId="+postId+"&prev="+prev+"&next="+next;
		
	}
	
	@RequestMapping("/likePostFromSeePost")
	public String likePostFromSeePost( @RequestParam("userId") Integer userId,
										 @RequestParam("postId") Integer postId,
										 	@RequestParam("profileId") Integer profileId,
										  	 Model model ) {
		
		try {
			postService.likePost(userId, postId);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/seePost?userId="+userId+"&postId="+postId+"&profileId="+profileId;
		
	}
	
	@RequestMapping("/dislikePostFromSeePost")
	public String dislikePostFromSeePost(@RequestParam("userId") Integer userId,
											 @RequestParam("postId") Integer postId,
											 	@RequestParam("profileId") Integer profileId,
											  	 Model model) {
		
		try {
			postService.dislikePost(userId, postId);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/seePost?userId="+userId+"&postId="+postId+"&profileId="+profileId;
	}
	
	
	@RequestMapping("/dislikePost")
	public String dislikePost(@RequestParam("userId") Integer userId,
								 @RequestParam("postId") Integer postId,
								  @RequestParam("prev") Integer prev,
								   @RequestParam("next") Integer next,
								  	 Model model) {
		
		try {
			postService.dislikePost(userId, postId);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/home?userId="+userId+"&postId="+postId+"&prev="+prev+"&next="+next;
	}
	
	@RequestMapping("/seePost")
	public String seePost(@RequestParam("postId") Integer postId,
							@RequestParam("userId") Integer userId,
								@RequestParam("profileId") Integer profileId,
								Model model) {
		
		try {
			
			User user = userService.getUserWithId(userId);
			Boolean connection = userService.checkConnection(userId, profileId);
			Post post = postService.getPostWithId(postId);
			User postAdmin = userService.getUserWithId( post.getUserId() );
			List<Message> homeFriendsMessages = messageService.gethomeMessagesFromFriends(userId);
			List<Notification> notificationsForHome = notificationService.getNotificationForHome(userId);
			List<Post> morePhotosFromUserOfPost = postService.morePhotosFromUserOfPost(profileId, postId);
			
			model.addAttribute("user", user);
			model.addAttribute("post", post);
			model.addAttribute("postAdmin", postAdmin);
			model.addAttribute("homeFriendsMessages",homeFriendsMessages);
			model.addAttribute("notificationsForHome", notificationsForHome);
			model.addAttribute("connection",connection);
			model.addAttribute("morePhotosFromUserOfPost",morePhotosFromUserOfPost);
			model.addAttribute("userService", userService);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("userId", userId);
		model.addAttribute("postId", postId);
		model.addAttribute("profileId",profileId);
		
		return "views/seePost";
	}
	
	@RequestMapping("/deletePost")
	public String deletePost(@RequestParam("profileId") Integer profileId,
								@RequestParam("userId") Integer userId,
									@RequestParam("postId") Integer postId,
									Model model) {
		
		try {
			postService.deletePost(postId);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/profile?userId="+userId+"&profileId="+profileId;
	}
}
