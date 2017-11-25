package com.intellectDesign.springboot.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.intellectDesign.springboot.model.User;

@Component
public class UserDetailsHandler {
	
	private static List<User> userList = new ArrayList<User>();
	


	public void resgisterUser(User user) {
		userList.add(user);
		
	}

}
