package com.bankapp.model;

public interface BankResponse {

	public boolean getStatus();
	public void setStatus(boolean status);

	public void setMessage(String message);
	public String getMessage();

}
