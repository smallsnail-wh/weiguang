package com.wh.weiguang.login.validate.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DefaultSmscodeSender implements SmsCodeSender {

	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Override
	public void send(String mobile, String code) {

		/*换成正在的发送短信接口*/
		System.out.println("向手机：" + mobile + "发送短信验证码" + code);getClass();
		log.info("***********************************");
		log.info("向手机：" + mobile + "发送短信验证码" + code);
		log.info("***********************************");
	}

}
