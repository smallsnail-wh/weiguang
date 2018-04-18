package com.wh.weiguang.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix="weiguang.properties")
@Configuration
public class MyProperties {
	
	private SmsCodeProperties smsCodeProperties = new SmsCodeProperties();
	
	private PathsProperties pathsProperties = new PathsProperties();

	public SmsCodeProperties getSmsCodeProperties() {
		return smsCodeProperties;
	}

	public void setSmsCodeProperties(SmsCodeProperties smsCodeProperties) {
		this.smsCodeProperties = smsCodeProperties;
	}

	public PathsProperties getPathsProperties() {
		return pathsProperties;
	}

	public void setPathsProperties(PathsProperties pathsProperties) {
		this.pathsProperties = pathsProperties;
	}
	
	
}
