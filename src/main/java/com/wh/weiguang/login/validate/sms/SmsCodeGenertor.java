package com.wh.weiguang.login.validate.sms;

import java.io.IOException;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wh.weiguang.login.validate.ValidateCodeGenerator;
import com.wh.weiguang.properties.MyProperties;

@Service
public class SmsCodeGenertor implements ValidateCodeGenerator {

	public static final String VERIFY_CODES = "0123456789";
	
	@Autowired
	private MyProperties myProperties;
	
	@Override
	public SmsCode createSmsCode(HttpServletRequest req) throws IOException {
		String code = generateVerifyCode(myProperties.getSmsCodeProperties().getLendth());
		return new SmsCode( code, myProperties.getSmsCodeProperties().getExpireIn());
	}

	public static String generateVerifyCode(int verifySize) {
		String sources = VERIFY_CODES;
		int codesLen = sources.length();
		Random rand = new Random(System.currentTimeMillis());
		StringBuilder verifyCode = new StringBuilder(verifySize);
		for (int i = 0; i < verifySize; i++) {
			verifyCode.append(sources.charAt(rand.nextInt(codesLen - 1)));
		}
		return verifyCode.toString();
	}
	
}
