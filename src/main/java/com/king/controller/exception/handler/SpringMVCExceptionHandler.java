package com.king.controller.exception.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.king.controller.exception.ColumnValueDuplicateException;
import com.king.controller.exception.SqlExecuteErrorException;
import com.king.dto.CallbackDataDTO;

//异常处理类
@ControllerAdvice
@ResponseBody
public class SpringMVCExceptionHandler {
	
	private final static Logger logger = LoggerFactory.getLogger(SpringMVCExceptionHandler.class);
	
	//数据库执行异常处理
	@ExceptionHandler({SqlExecuteErrorException.class})
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public CallbackDataDTO handlerSqlExecuteErrorException(Exception exception)
	{
        logger.error("数据库执行异常:" + exception.getMessage());
        return CallbackDataDTO.build(false, exception.getMessage());
	}
	
	//数据校验失败异常处理
	@ExceptionHandler({ConstraintViolationException.class})
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public CallbackDataDTO handlerConstraintViolationException(ValidationException exception)
	{
		List<Map<String,String>> exceptionArray = new ArrayList<>();
        if(exception instanceof ConstraintViolationException){
	        ConstraintViolationException exs = (ConstraintViolationException) exception;
	        
	        Set<ConstraintViolation<?>> constraintViolations = exs.getConstraintViolations();
	        for (ConstraintViolation<?> item : constraintViolations) 
	        {
	        	if (item.getExecutableParameters()[1] instanceof BeanPropertyBindingResult)
	        	{
	        		BeanPropertyBindingResult error = (BeanPropertyBindingResult)item.getExecutableParameters()[1];
	        		for (FieldError ele : error.getFieldErrors())
	        		{
	        			Map<String,String> temp = new HashMap<>();
	        			temp.put(ele.getField(), ele.getDefaultMessage());
	        			exceptionArray.add(temp);
	        		}
	        	}
	        	break;
	        }
        }
        logger.error("数据校验失败:" + exceptionArray);
        return CallbackDataDTO.build(false, exceptionArray);
	}
	
/*	//用户未找到异常处理
	@ExceptionHandler({UsernameNotFoundException.class})
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public CallbackDataDTO handlerUsernameNotFoundException(Exception exception)
	{
        logger.error("用户未找到异常:" + exception.getMessage());
        return CallbackDataDTO.build(false, exception.getMessage());
	}*/
	
	//字段唯一性查询异常处理
	@ExceptionHandler({ColumnValueDuplicateException.class})
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public CallbackDataDTO handlerColumnValueDuplicateException(Exception exception)
	{
        logger.error("字段重名查询异常:" + exception.getMessage());
        return CallbackDataDTO.build(false, exception.getMessage());
	}
	
	//其他所有运行时异常
	@ExceptionHandler({RuntimeException.class})
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public CallbackDataDTO handlerRuntimeException(Exception exception)
	{
        logger.error("系统内部异常:" + exception.getMessage());
        exception.printStackTrace();
        return CallbackDataDTO.build(false, exception.getMessage());
	}

	
	
}
