package com.showme.services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.showme.beans.ConversationTable;
import com.showme.beans.Post;
import com.showme.beans.User;
import com.showme.database.PostDB;
import com.showme.database.UserDB;
import com.showme.recommendation.UserRecommendationKNN;

@Component
public class UserService {

	@Autowired
	User user;
	
	@Autowired
	UserDB userDB;
	
	@Autowired
	UserRecommendationKNN userRecommendationKNN;
	
	@Autowired
	PostService postService;
	
	public User getUserWithId(Integer userId) throws ClassNotFoundException, SQLException, IOException {
		return userDB.getUser(userId);
	}
	
	public String returnNameOfUser(Integer userId) throws ClassNotFoundException, SQLException, IOException {
		return userDB.returnNameOfUser(userId);
	}
	
	public List<User> getUsersFriends(Integer userId) throws ClassNotFoundException, IOException, SQLException{
		return userDB.getFriendsOfUser(userId);
	}
	
	public String getProfileImageOfUser(Integer userId) {
		
		String imageStr = null;
		
		try {
			imageStr =  userDB.getProfileImageOfUser(userId);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return imageStr;
	}

	public List<User> homeFriendRecommendation(Integer userId) throws ClassNotFoundException, SQLException, IOException {
		
		List<User> users = userDB.getInterestOfUsersOtherThanFriends(userId);
		User user = userDB.getInterestOfUser(userId);
		List<User> listOfRecommendedUsers = userRecommendationKNN.recommendUsersForUser(5, user, users);
		
		List<User> recommendedUsers = new ArrayList<>();
		for (User u : listOfRecommendedUsers) {
			if(u.getId()!=userId)
				recommendedUsers.add(userDB.getUser(u.getId()));
		}
		return recommendedUsers;
	}
	
	public List<Integer> getUserFriendsIds(Integer userId) throws SQLException, ClassNotFoundException{
		return userDB.getUserFriendsIds(userId);
	}
	
	public List<ConversationTable> getAllConversationTablesName(Integer userId) throws ClassNotFoundException, SQLException{
		return userDB.getAllConversationTablesName(userId);
	}

	public List<User> exploreFriendRecommendation(Integer userId) throws ClassNotFoundException, SQLException, IOException {
		
		List<User> users = userDB.getInterestOfUsersOtherThanFriends(userId);
		User user = userDB.getInterestOfUser(userId);
		List<User> listOfRecommendedUsers = userRecommendationKNN.recommendUsersForUser(5, user, users);
		
		List<User> recommendedUsers = new ArrayList<>();
		for (User u : listOfRecommendedUsers) {
			if(u.getId()!=userId)
				recommendedUsers.add(userDB.getUser(u.getId()));
		}
		
		return recommendedUsers;
	}

	public List<User> getOtherUsersWhichAreNotAFriend(Integer userId) throws SQLException, IOException, ClassNotFoundException {
		return userDB.getOtherUsersWhichAreNotAFriend(userId);
	}

	public User getUserForProfile(Integer profileId) throws ClassNotFoundException, SQLException, IOException {
	
		User user = userDB.getUser(profileId);
		user.setPosts(postService.getUserPosts(profileId));
		user.setFriendsId(userDB.getUserFriendsIds(profileId));
		
		return user;
	}

	public List<Integer> getUserFriendsIdsAndFollowings(Integer userId) throws ClassNotFoundException, SQLException {
		return userDB.getUserFriendsIdsAndFollowings(userId);
	}

	public boolean checkConnection(Integer userId, Integer anotherUserId) throws ClassNotFoundException, SQLException {
		
			return userDB.checkConnection(userId, anotherUserId);
		
	}
	
	public Post getLatestPostByUser(Integer userId) {
		try {
			return postService.getLatestPost(userId);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Post();
	}

	public List<User> exploreFriends(Integer userId) throws ClassNotFoundException, SQLException, IOException {
		return userDB.exploreFriends(userId);
	}

	public String getConversationTableName(Integer userId, Integer friendId) throws ClassNotFoundException, SQLException {
		return userDB.getConversationTableName(userId, friendId);
	}
}
