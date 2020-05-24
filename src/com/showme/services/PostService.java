package com.showme.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.showme.beans.Comment;
import com.showme.beans.Like;
import com.showme.beans.Post;
import com.showme.database.PostDB;

@Component
public class PostService {

	@Autowired
	PostDB postDB;
	
	@Autowired
	NotificationService notificationService;
	
	public List<Post> getHomePosts(Integer userId) throws ClassNotFoundException, SQLException, IOException{
		return postDB.getHomePosts(userId);
	}
	
	public List<Post> getUserPosts(Integer userId) throws ClassNotFoundException, SQLException, IOException{
		return postDB.getUserPosts(userId);
	}
	public Post getLatestPost(Integer userId) throws ClassNotFoundException, SQLException, IOException {
		return postDB.getLatestPost(userId);
	}
	public void likePost(Integer notificationFromUserId, Integer postId) throws ClassNotFoundException, SQLException {
		Integer likeId = postDB.likePost(notificationFromUserId, postId);
		Integer notificationForUserId = postDB.getUserIdFromPostId(postId);
		notificationService.likeNotification(notificationForUserId, notificationFromUserId, postId, likeId);
	}
	
	public void dislikePost(Integer userId, Integer postId) throws ClassNotFoundException, SQLException {
		postDB.dislikePost(userId, postId);
	}
	
	public Like getLikeInformation(Integer likeId) throws ClassNotFoundException, SQLException {
		return postDB.getLikeInformation(likeId);
	}
	
	public void commentPost(Integer userId, Integer postId, String cmt) throws ClassNotFoundException, SQLException {
		
		long millis = System.currentTimeMillis();
		Comment comment = new Comment();
		comment.setUserId(userId);
		comment.setPostId(postId);
		comment.setComment(cmt);
		comment.setDate(new Date(millis));
		comment.setTime(new Time(millis));

		Integer commentId = postDB.commentPost(comment);
		Integer notificationForUserId = postDB.getUserIdFromPostId(comment.getPostId());
		notificationService.likeNotification(notificationForUserId, comment.getUserId(), comment.getPostId(), commentId);
	}
	
	public Comment getCommentInformation(Integer commentId) throws ClassNotFoundException, SQLException {
		return postDB.getCommentInformation(commentId);
	}

	public Post getPostWithId(Integer postId) throws ClassNotFoundException, SQLException, IOException {
		return postDB.getPostWithId(postId);
	}

	public List<Post> getRandomPicturesOfUserWhichAreNotFriend(Integer userId) throws ClassNotFoundException, SQLException, IOException {
		return postDB.getRandomPicturesOfUserWhichAreNotFriend(userId);
	}

	public void deletePost(Integer postId) throws ClassNotFoundException, SQLException {
		postDB.deletePost(postId);
	}

	public void insertPost(Integer userId, String postUrl, String postName) throws ClassNotFoundException, SQLException, IOException {
		
		System.out.println("see: "+postName);
		
		File pathFile = new File(postName);
		
		/*
		 * boolean flag = pathFile.createNewFile();
		 * 
		 * if(flag==true) { System.out.println("how you doin?"); }
		 */
		FileOutputStream fos = new FileOutputStream(pathFile);
		
		String b64 = postUrl.substring(postUrl.indexOf(",") + 1);
		
	    byte[] decoder = Base64.getDecoder().decode(b64);
		
	    fos.write(decoder);
	    
	    fos.close();
	    
		postDB.insertPost(userId, pathFile.getPath());
		
		//notificationService.postNotification( userId, statusId);
		
	}

	public List<Post> morePhotosFromUserOfPost(Integer userId,Integer postId) throws ClassNotFoundException, SQLException, IOException {
		return postDB.morePhotosFromUserOfPost(userId,postId);
	}
	
	
}
