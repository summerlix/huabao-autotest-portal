package com.king.dto;

import java.io.Serializable;

public class CallbackErrorDTO implements Serializable{

	
	private static final long serialVersionUID = 1L;

	private String error;
	
	private String error_message;
	
	public CallbackErrorDTO(String error, String error_message) {
		super();
		this.error = error;
		this.error_message = error_message;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getError_message() {
		return error_message;
	}

	public void setError_message(String error_message) {
		this.error_message = error_message;
	}

	@Override
	public String toString() {
		return "CallbackErrorDTO [error=" + error + ", error_message=" + error_message + "]";
	}
	
	
	
	
	
}
