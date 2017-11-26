package com.intellectDesign.springboot.model;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class HttpCustomResponse {

	private String resMsg;
	private String userId;
	private List<ValidationArray> valErrors;
	public String getResMsg() {
		return resMsg;
	}
	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<ValidationArray> getValErrors() {
		return valErrors;
	}
	public void setValErrors(List<ValidationArray> valErrors) {
		this.valErrors = valErrors;
	}
	
	
}
