package com.intellectDesign.springboot.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.intellectDesign.springboot.model.HttpCustomResponse;
import com.intellectDesign.springboot.model.User;
import com.intellectDesign.springboot.model.ValidationArray;

@Component
public class CustomExceptionHandler {

	@Autowired
	HttpCustomResponse httpCustomResponse;
	public HttpCustomResponse createExceptionResponse(User user, int status) {
		List<ValidationArray> vllist = new ArrayList<ValidationArray>();
		Date date = new Date();
		if(user!=null && (user.getBirthDate()==null || user.getBirthDate()!=null && user.getBirthDate().after(date))){
			ValidationArray validationArray = createValidationError("401","Birthdate","Birthdate should not be null or future date");
			vllist.add(validationArray);
		}
		if(user!=null &&(user.getEmail()==null || (user.getEmail()!=null && user.getEmail().isEmpty()))){
			ValidationArray validationArray = createValidationError("402","Email","email cantnot be empty");
			vllist.add(validationArray);
		}

		if (user!=null && (user.getfName()==null || (user.getfName()!=null && user.getfName().isEmpty()))){
			ValidationArray validationArray = createValidationError("403","fname","Firstname cannot be empty");
			vllist.add(validationArray);
		}
		if(user!=null && (user.getlName()==null ||(user.getlName()!=null&&user.getlName().isEmpty()))){
			ValidationArray validationArray = createValidationError("404","lName","Lastname cannot be empty");
			vllist.add(validationArray);
		}
		if(user!=null && user.getPinCode()<=0){
			ValidationArray validationArray = createValidationError("405","Pincode","Pincode should be valid value");
			vllist.add(validationArray);
		}
		if(status == 0){
			ValidationArray validationArray = createValidationError("406","email","email id already attached to another user");
			vllist.add(validationArray);
		}
		if(status == 2){
			ValidationArray validationArray = createValidationError("406","id","Not a registered user");
			vllist.add(validationArray);
		}
		httpCustomResponse.setResMsg("Transaction Failed");
		httpCustomResponse.setUserId(user.getId());
		httpCustomResponse.setValErrors(vllist);
		return httpCustomResponse;
	}
	public HttpCustomResponse createExceptionResponseforUpdate(User user, int status) {
		List<ValidationArray> vllist = new ArrayList<ValidationArray>();
		Date date = new Date();
		if(user!=null && (user.getBirthDate()==null || user.getBirthDate()!=null && user.getBirthDate().after(date))){
			ValidationArray validationArray = createValidationError("401","Birthdate","Birthdate should not be null or future date");
			vllist.add(validationArray);
		}
		if(user!=null && (user.getfName()!=null && !user.getfName().isEmpty()) || (user.getlName()!=null&& !user.getlName().isEmpty())){
			ValidationArray validationArray = createValidationError("401","multipleFields","user ID is mandetory field and only pincode and Dateof bith can be updated with proper value");
			vllist.add(validationArray);
		}
		httpCustomResponse.setResMsg("Transaction Failed");
		httpCustomResponse.setUserId(user.getId());
		httpCustomResponse.setValErrors(vllist);
		return httpCustomResponse;
	}
	private ValidationArray createValidationError(String code, String field, String message) {
		ValidationArray validationArray = new ValidationArray();
		validationArray.setCode(code);
		validationArray.setField(field);
		validationArray.setMessage(message);
		return validationArray;
	}
	public HttpCustomResponse createExceptionResponseforDeletion(User user, int flag) {
		List<ValidationArray> vllist = new ArrayList<ValidationArray>();
		if(flag == 2){
			ValidationArray validationArray = createValidationError("406","id","Not a registered user");
			vllist.add(validationArray);
		}if(flag == 3){
			ValidationArray validationArray = createValidationError("406","id","user ID is mandetory");
			vllist.add(validationArray);
		}
		httpCustomResponse.setResMsg("Transaction Failed");
		httpCustomResponse.setUserId(user.getId());
		httpCustomResponse.setValErrors(vllist);
		return httpCustomResponse;
	}

}
