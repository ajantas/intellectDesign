package com.intellectDesign.springboot.service.rs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.intellectDesign.springboot.model.HttpCustomResponse;
import com.intellectDesign.springboot.model.User;
import com.intellectDesign.springboot.model.ValidationArray;
import com.intellectDesign.springboot.util.CustomExceptionHandler;
import com.intellectDesign.springboot.util.InputValidationHandler;
import com.intellectDesign.springboot.util.UserDetailsHandler;

@Controller
@RequestMapping("/intellectDesign/v3/user")
public class RestService {

	@Autowired
	InputValidationHandler inputValidationHandler;
	@Autowired
	UserDetailsHandler UserDetailsHandler;
	@Autowired
	HttpCustomResponse httpCustomResponse;
	@Autowired
	CustomExceptionHandler customexceptionHandler;
	
	@RequestMapping(value = "/registration", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity createUser(@RequestBody User user) {
		
		System.out.println(user+" user");
		int status = inputValidationHandler.validationOfNewUserdetails(user);
		if(status == 0){	
			String message = UserDetailsHandler.resgisterUser(user);
			if(message!=null){
			httpCustomResponse.setResMsg("Account created successfully");
			httpCustomResponse.setUserId(message);
			httpCustomResponse.setValErrors(null);
			return new ResponseEntity(httpCustomResponse,HttpStatus.OK);
			}
			else{
				httpCustomResponse = customexceptionHandler.createExceptionResponse(user,status);
				return new ResponseEntity(httpCustomResponse,HttpStatus.BAD_REQUEST);
			}
		}
		else{
			httpCustomResponse = customexceptionHandler.createExceptionResponse(user,status);
				return new ResponseEntity(httpCustomResponse,HttpStatus.BAD_REQUEST);
			}
		}
	

	@RequestMapping(value = "/updation", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity updateDetails(@RequestBody User user) {
		System.out.println(user+" user");
		boolean status = inputValidationHandler.validationOfUpdateUserdetails(user);
		int flag = 100;
		String message;
		if(status==true){	
			message = UserDetailsHandler.updateUser(user);
			if(message.equalsIgnoreCase("Not a registered user")){
				flag = 2;
			}
		}
		else{
			message =  "user ID is mandetory field and only Email and Dateof bith can be updated with proper value";
			flag = 3;
		}
		
		if(flag == 100){
			httpCustomResponse.setResMsg(message);
			httpCustomResponse.setUserId(user.getId());
			httpCustomResponse.setValErrors(null);
			return new ResponseEntity(httpCustomResponse,HttpStatus.OK);
		}
		else{
			httpCustomResponse = customexceptionHandler.createExceptionResponseforUpdate(user,flag);
			return new ResponseEntity(httpCustomResponse,HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/deletion", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity deleteUser(@RequestBody User user) {
		System.out.println(user+" user");
		boolean status = inputValidationHandler.validationOfDeleteUserdetails(user);
		int flag = 100;
		String message;
		if(status==true){	
			message =  UserDetailsHandler.deleteUser(user);
			if(message.equalsIgnoreCase("Not a registered user")){
				flag = 2;
			}
		}
		else{
			flag = 3;
			message =  "user ID is mandetory";
		}
		if(flag == 100){
			httpCustomResponse.setResMsg(message);
			httpCustomResponse.setUserId(user.getId());
			httpCustomResponse.setValErrors(null);
			return new ResponseEntity(httpCustomResponse,HttpStatus.OK);
		}
		else{
			httpCustomResponse = customexceptionHandler.createExceptionResponseforDeletion(user,flag);
			return new ResponseEntity(httpCustomResponse,HttpStatus.BAD_REQUEST);
		}
	}
	
}
