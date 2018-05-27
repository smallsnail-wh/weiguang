package com.wh.weiguang.login.validate.sms;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody
public class SmsCodeFailureHandler {

	@ExceptionHandler(SmsCodeException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public void handleValidateFilureException(HttpServletRequest request, HttpServletResponse response,SmsCodeException ex) throws IOException{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().write("{\"error\":403,\"message\":\""+ex.getMessage()+"\"}");
	}
	
}
