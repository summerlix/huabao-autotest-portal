package com.king.controller.exception;


//数据库执行错误异常
public class SqlExecuteErrorException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SqlExecuteErrorException() {}
	
	public SqlExecuteErrorException(String msg)
	{
		super(msg);
	}
}
