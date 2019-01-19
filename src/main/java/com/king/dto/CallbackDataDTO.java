package com.king.dto;

import java.io.Serializable;

public class CallbackDataDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private static CallbackDataDTO instance = new CallbackDataDTO();
	
	public static CallbackDataDTO build(boolean success, String message)
	{
		instance.setSuccess(success);
		instance.setData(null);
		instance.setMessage(message);
		return instance;
	}
	
	public static CallbackDataDTO build(boolean success, Object data)
	{
		instance.setSuccess(success);
		instance.setData(data);
		instance.setMessage(null);
		return instance;
	}
	
	private CallbackDataDTO() {
		super();
	}

	private boolean success;
	
	private Object data;
	
	private String message;
	
	

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "CallbackData [success=" + success + ", data=" + data + ", message=" + message + "]";
	}
	
	
	
}
