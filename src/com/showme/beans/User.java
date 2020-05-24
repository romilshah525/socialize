package com.showme.beans;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class User {

	private Integer id;
	private String name;
	private String email;
	private String contact;
	private String password;
	private String profileImage;
	
	private List<Integer> friendsId;

	private List<Integer> userInterest = new ArrayList<Integer>(10);
	
	private List<Post> posts;
	
	private List<Message> messages;
	
	private Long lastConversation;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public List<Integer> getFriendsId() {
		return friendsId;
	}

	public void setFriendsId(List<Integer> friendsId) {
		this.friendsId = friendsId;
	}

	public List<Integer> getUserInterest() {
		return userInterest;
	}

	public void setUserInterest(List<Integer> userInterest) {
		this.userInterest = userInterest;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public Long getLastConversation() {
		return lastConversation;
	}

	public void setLastConversation(Long lastConversation) {
		this.lastConversation = lastConversation;
	}
	
	
}
