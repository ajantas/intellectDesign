package com.intellectDesign.springboot.util;


import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;
import com.intellectDesign.springboot.model.User;

@Component
public class InputValidationHandler {

	
	public boolean validationOfNewUserdetails(User user){
		boolean status = true;
		 Date date = new Date();
		 if(user!=null && (user.getBirthDate()==null || user.getBirthDate()!=null && user.getBirthDate().after(date)) || (user.getEmail()==null || (user.getEmail()!=null && user.getEmail().isEmpty())) || (user.getfName()==null || (user.getfName()!=null && user.getfName().isEmpty())) || (user.getlName()==null ||(user.getlName()!=null&&user.getlName().isEmpty())) || user.getPinCode()<=0){
			status = false;
		}
		return status;
	}
	public boolean validatDuplicateUser(User user,List<User> useres){
		boolean status = true;
		for(User u : useres){
			if(u.getEmail().equalsIgnoreCase(user.getEmail()) && u.isActive()){
				status = false;
				break;
			}
		}
		return status;
	}
	
	
	public boolean validatExsistingUser(User user,List<User> useres){
		boolean status = true;
		for(User u : useres){
			if(u.getId().equalsIgnoreCase(user.getId()) && u.isActive()){
				status = false;
				break;
			}
		}
		return status;
	}
	public boolean validationOfUpdateUserdetails(User user) {
		 Date date = new Date();
		 if(user!=null && (user.getfName()!=null && !user.getfName().isEmpty()) || (user.getlName()!=null&& !user.getlName().isEmpty())){
				return false;
			}
		 else if(user!=null && (user.getBirthDate()==null || user.getBirthDate()!=null && user.getBirthDate().after(date) || (user.getId()==null || (user.getId()!=null && user.getId().isEmpty())))){
				return false;
			}
		 else{
			 return true;
		 }
	}
	public boolean validationOfDeleteUserdetails(User user) {
		if(user!=null && (user.getId()==null || (user.getId()!=null && user.getId().isEmpty())))
		return false;
		else
		return true;
	}
	
}
