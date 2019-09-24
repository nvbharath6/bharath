package com.bankapp.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BankResponseImpl implements BankResponse {

	private boolean status = false;	
	private String message;
		
	public BankResponseImpl(){
			
	}
	
	public boolean getStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;		
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}	  
}
