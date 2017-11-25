package com.intellectDesign.springboot.service.rs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	 @RequestMapping(method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	    public @ResponseBody String createUser(@RequestBody User user) {
		System.out.println(user+" user");
		
		 boolean status = inputValidationHandler.validationOfUserdetails(user);
		 
		 if(status==true){
			 UserDetailsHandler.resgisterUser(user);
		 	return "123";
		 }
		 else
			 return "234";
	    }

}
