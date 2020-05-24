package com.showme.recommendation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.showme.beans.User;


@Component
public class UserRecommendationKNN {
	private Float euclideanDistance(List<Integer> list1, List<Integer> list2){
		
	Integer sum = 0;
	
	for (int i = 0; i < list1.size(); i++) {
		
		int term = (list1.get(i) - list2.get(i)) * (list1.get(i) - list2.get(i));
	    sum += term;
		
	}
	
    String convertedSum = Integer.toString(sum);
    double convertedToDoubleSum = Double.parseDouble(convertedSum);
    double distance = Math.abs (Math.sqrt (convertedToDoubleSum ) );
    String convertedDistance = Double.toString(distance);
    
    return Float.parseFloat(convertedDistance);
	
	}
	
	public List<User> recommendUsersForUser(Integer numberRecommendUser, User u, List<User> users) {
		
		List<Float> euclideandistance = new ArrayList<>();
		List<Float> shorted = new ArrayList<>();
		
		for (User user : users) {
			
			euclideandistance.add(euclideanDistance(u.getUserInterest(), user.getUserInterest()));
			shorted.add(euclideanDistance(u.getUserInterest(), user.getUserInterest()));
		}
		
		Collections.sort(shorted);
	
		List<Float> ed = null;
		
		if(numberRecommendUser < shorted.size())
			ed = shorted.subList(0, numberRecommendUser);
		else
			ed = shorted;
		
		for (Float float1 : ed) {
			System.out.println(float1);
		}
		
		List<User> recommendUsers = new ArrayList<>();
		
		for (Float e : ed) {
			recommendUsers.add(users.get(euclideandistance.indexOf(e)));
		}
		
		return recommendUsers;
	}
	
}
