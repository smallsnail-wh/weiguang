package com.wh.weiguang.login.validate;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.wh.weiguang.login.validate.sms.SmsCode;

/**
 * 
 * @author wanghuan
 *
 */
public interface ValidateCodeGenerator {
	
	SmsCode createSmsCode(HttpServletRequest req) throws IOException;

}
