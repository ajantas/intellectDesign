package com.intellectDesign.springboot.service.rs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.intellectDesign.springboot.model.User;
import com.intellectDesign.springboot.util.InputValidationHandler;
import com.intellectDesign.springboot.util.UserDetailsHandler;

@Controller
@RequestMapping("/user")
public class RestService {

	@Autowired
	InputValidationHandler inputValidationHandler;
	@Autowired
	UserDetailsHandler UserDetailsHandler;
	@RequestMapping(value = "/registration", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String createUser(@RequestBody User user) {
		System.out.println(user+" user");
		boolean status = inputValidationHandler.validationOfNewUserdetails(user);
		if(status==true){	
			return UserDetailsHandler.resgisterUser(user);
		}
		else{
			return "Mandetory field is missing";
		}
	}

	@RequestMapping(value = "/updation", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String updateDetails(@RequestBody User user) {
		System.out.println(user+" user");
		boolean status = inputValidationHandler.validationOfUpdateUserdetails(user);
		if(status==true){	
			return UserDetailsHandler.updateUser(user);
		}
		else{
			return "user ID is mandetory field and only Email and Dateof bith can be updated with proper value";
		}
	}
	
	@RequestMapping(value = "/deletion", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String deleteUser(@RequestBody User user) {
		System.out.println(user+" user");
		boolean status = inputValidationHandler.validationOfDeleteUserdetails(user);
		if(status==true){	
			return UserDetailsHandler.deleteUser(user);
		}
		else{
			return "user ID is mandetory";
		}
	}
	
}
