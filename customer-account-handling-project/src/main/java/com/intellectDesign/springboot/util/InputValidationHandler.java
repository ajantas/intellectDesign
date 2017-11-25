package com.intellectDesign.springboot.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;
import com.intellectDesign.springboot.model.User;

@Component
public class InputValidationHandler {

	
	public boolean validationOfUserdetails(User user){
		boolean status = true;
		 Date date = new Date();
		if(user.getBirthDate().after(date))
			status = false;
		return status;
	}
}
