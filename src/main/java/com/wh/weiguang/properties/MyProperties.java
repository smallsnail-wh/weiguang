package com.wh.weiguang.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix="weiguang.properties")
@Configuration
public class MyProperties {
	
	private SmsCodeProperties smsCodeProperties = new SmsCodeProperties();

	public SmsCodeProperties getSmsCodeProperties() {
		return smsCodeProperties;
	}

	public void setSmsCodeProperties(SmsCodeProperties smsCodeProperties) {
		this.smsCodeProperties = smsCodeProperties;
	}
	
}
