package com.showme.beans;

import org.springframework.stereotype.Component;

@Component
public class Notification {

	private int id;
	private int notificationForUserId;
	private int notificationFromUserId;
	private String typeOfNotification;
	private int postId;
	private int likeId;
	private int commentId;
	private int requestId;
	private int groupId;
	private int statusId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNotificationForUserId() {
		return notificationForUserId;
	}
	public void setNotificationForUserId(int notificationForUserId) {
		this.notificationForUserId = notificationForUserId;
	}
	public int getNotificationFromUserId() {
		return notificationFromUserId;
	}
	public void setNotificationFromUserId(int notificationFromUserId) {
		this.notificationFromUserId = notificationFromUserId;
	}
	public String getTypeOfNotification() {
		return typeOfNotification;
	}
	public void setTypeOfNotification(String typeOfNotification) {
		this.typeOfNotification = typeOfNotification;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public int getLikeId() {
		return likeId;
	}
	public void setLikeId(int likeId) {
		this.likeId = likeId;
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	
	
}
