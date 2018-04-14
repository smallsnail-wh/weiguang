package com.wh.weiguang.login.validate.sms;

import org.springframework.stereotype.Service;

@Service
public class DefaultSmscodeSender implements SmsCodeSender {

	@Override
	public void send(String mobile, String code) {

		/*换成正在的发送短信接口*/
		System.out.println("向手机：" + mobile + "发送短信验证码" + code);

	}

}
