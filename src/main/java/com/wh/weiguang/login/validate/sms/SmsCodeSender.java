package com.wh.weiguang.login.validate.sms;

public interface SmsCodeSender {

	void send(String mobile, String code);

}
