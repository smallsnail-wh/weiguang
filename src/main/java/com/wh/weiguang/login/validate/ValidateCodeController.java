package com.wh.weiguang.login.validate;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wh.weiguang.login.validate.sms.SmsCode;
import com.wh.weiguang.login.validate.sms.SmsCodeSender;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
public class ValidateCodeController {

	@Autowired
	private SmsCodeSender smsCodeSender;

	@Autowired
	private ValidateCodeGenerator smsCodeGenerator;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@ApiOperation(value = "发送短息验证码")
	@GetMapping("/code/sms")
	public void createSmsCode(HttpServletRequest request, HttpServletResponse response,
			@ApiParam(value = "接受短息的手机号") @RequestParam("mobile") String mobile)
			throws IOException, ServletRequestBindingException {
		SmsCode smsCode = smsCodeGenerator.createSmsCode(request);

		//String mobile = ServletRequestUtils.getRequiredStringParameter(request, "mobile");

		/* 换成redis */
		/* request.getSession().setAttribute(SESSION_KEY, smsCode); */

		ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
		valueOperations.set(mobile, smsCode.getCode(), smsCode.getExpireTime(), TimeUnit.SECONDS);

		smsCodeSender.send(mobile, smsCode.getCode());
	}

}
