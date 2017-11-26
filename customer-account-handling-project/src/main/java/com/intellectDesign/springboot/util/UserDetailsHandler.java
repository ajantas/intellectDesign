package com.intellectDesign.springboot.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.intellectDesign.springboot.model.User;

@Component
public class UserDetailsHandler {
	
	private static List<User> userList = new ArrayList<User>();
	
	@Autowired
	InputValidationHandler inputValidationHandler;

	public String resgisterUser(User user) {
		if(inputValidationHandler.validatDuplicateUser(user, userList)){
			user.setId(user.getId()+user.getEmail());
			user.setActive(true);
			userList.add(user);
			System.out.println(user.getId());
			return user.getId();
		}
		else{
			return "emailid already registered";
		}
		
	}

	public String updateUser(User user) {
		if(!inputValidationHandler.validatExsistingUser(user, userList)){
			userList.forEach(activeuser -> {
				if(activeuser.getId().equalsIgnoreCase(user.getId())){
					if(user.getPinCode()>0){
						activeuser.setPinCode(user.getPinCode());
					}
					if(user.getBirthDate()!=null){
						activeuser.setBirthDate(user.getBirthDate());
					}
				}
			});
			return "Account updated";
		}
		else
			return "Not a registered user";
	}

	public String deleteUser(User user) {
		if(!inputValidationHandler.validatExsistingUser(user, userList)){
			userList.forEach(activeuser -> {
				if(activeuser.getId().equalsIgnoreCase(user.getId())){
					activeuser.setActive(false);
				}
			});
			return "Account is successfully deactivated";
	}
		else
			return "Not a registered user";
	}
}
