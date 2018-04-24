package com.wh.weiguang.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

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
	
}
