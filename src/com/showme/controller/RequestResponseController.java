package com.showme.controller;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.showme.services.RequestResponseService;

@Controller
public class RequestResponseController {

	@Autowired
	RequestResponseService requestResponseService;
	
	@RequestMapping("/followFromHome")
	public String followFromHome(@RequestParam("userId") Integer userId,
									 @RequestParam("postId") Integer postId,
									  @RequestParam("prev") Integer prev,
									   @RequestParam("next") Integer next,
									    @RequestParam("requestPersonId") Integer requestPersonId,
									  	 Model model) {
		
		try {
			requestResponseService.followRequest(userId, requestPersonId);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/home?userId="+userId+"&postId="+postId+"&prev="+prev+"&next="+next;
	}
	
	
	@RequestMapping("/followFromExplore")
	public String followFromExplore(@RequestParam("userId") Integer userId,
									    @RequestParam("requestPersonId") Integer requestPersonId,
									  	 Model model) {
		
		try {
			requestResponseService.followRequest(userId, requestPersonId);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/exploreSection?userId="+userId;
	}
	
	
	@RequestMapping("/followFromSeePost")
	public String followFromSeePost(@RequestParam("userId") Integer userId,
									    @RequestParam("requestPersonId") Integer requestPersonId,
									    	@RequestParam("postId") Integer postId,
									    		Model model) {
		
		try {
			requestResponseService.followRequest(userId, requestPersonId);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/seePost?userId="+userId+"&postId="+postId+"&profileId="+requestPersonId;
	}
	
	@RequestMapping("/followFromProfile")
	public String followFromProfile(@RequestParam("userId") Integer userId,
									    @RequestParam("requestPersonId") Integer requestPersonId,
									    	@RequestParam("profileId") Integer profileId,
									    		Model model) {
		
		try {
			requestResponseService.followRequest(userId, requestPersonId);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/profile?userId="+userId+"&profileId="+requestPersonId;
	}
	
	@RequestMapping("/followFromExploreFriends")
	public String followFromExploreFriends(@RequestParam("userId") Integer userId,
											@RequestParam("requestPersonId") Integer requestPersonId,
									    		Model model) {
		
		try {
			requestResponseService.followRequest(userId, requestPersonId);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/exploreFriends?userId="+userId;
	}
	
	@RequestMapping("/acceptFollowRequest")
	public String acceptFollowRequest(@RequestParam("userId") Integer userId,
											@RequestParam("notificationId") Integer notificationId,
												Model model) {
		
		try {
			requestResponseService.acceptFollowRequest(userId, notificationId);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/notifications?userId="+userId;
	}
	
	@RequestMapping("/rejectFollowRequest")
	public String rejectFollowRequest(@RequestParam("userId") Integer userId,
											@RequestParam("notificationId") Integer notificationId,
												Model model) {
		
		try {
			requestResponseService.rejectFollowRequest(userId, notificationId);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/notifications?userId="+userId;
	}
	
}
