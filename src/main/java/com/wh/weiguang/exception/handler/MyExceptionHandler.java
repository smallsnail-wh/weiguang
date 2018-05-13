package com.wh.weiguang.exception.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.wh.weiguang.exception.AdcertiseException;
import com.wh.weiguang.exception.OverVtimesException;
import com.wh.weiguang.exception.RedPacketException;

@ControllerAdvice
@ResponseBody
public class MyExceptionHandler {

	@ExceptionHandler(AdcertiseException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public Map<String, Object> handlerMyException(AdcertiseException ex) {
		Map<String,Object> result = new HashMap<>();
		result.put("message", ex.getMessage());
		result.put("error type", "AdcertiseException");
		return result;
	}
	
	@ExceptionHandler(RedPacketException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public Map<String, Object> handlerRedPacketException(RedPacketException ex) {
		Map<String,Object> result = new HashMap<>();
		result.put("code", ex.getCode());
		result.put("message", ex.getMessage());
		result.put("error type", "RedPacketException");
		return result;
	}
	
	@ExceptionHandler(OverVtimesException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public Map<String, Object> handlerOverVtimesException(OverVtimesException ex) {
		Map<String,Object> result = new HashMap<>();
		result.put("message", ex.getMessage());
		result.put("error type", "OverVtimesException");
		return result;
	}
	
}
