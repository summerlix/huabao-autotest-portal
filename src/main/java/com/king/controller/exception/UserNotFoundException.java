package com.king.controller.exception;


//用户未找到异常
public class UserNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotFoundException() {}
	
	public UserNotFoundException(String msg)
	{
		super(msg);
	}
}
