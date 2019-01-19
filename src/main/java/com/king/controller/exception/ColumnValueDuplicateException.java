package com.king.controller.exception;


//重名查询异常
public class ColumnValueDuplicateException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ColumnValueDuplicateException(){}
	
	public ColumnValueDuplicateException(String msg){
		super(msg);
	}

}
