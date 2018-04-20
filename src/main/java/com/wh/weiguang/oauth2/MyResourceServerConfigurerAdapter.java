package com.wh.weiguang.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.wh.weiguang.login.validate.sms.SmsCodeFailureHandler;
import com.wh.weiguang.login.validate.sms.SmsCodeFilter;
import com.wh.weiguang.properties.MyProperties;

@Configuration
@EnableResourceServer
public class MyResourceServerConfigurerAdapter extends ResourceServerConfigurerAdapter {

	@Autowired
	private MyProperties myProperties;

	@Autowired
	private SmsCodeFailureHandler smsCodeFailureHandler;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	/*@Autowired
	private SmsCodeAutenticationSecurityConfig smsCodeAutenticationSecurityConfig;*/

	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		SmsCodeFilter smsCodeFilter = new SmsCodeFilter();
		smsCodeFilter.setMyProperties(myProperties);
		smsCodeFilter.setStringRedisTemplate(stringRedisTemplate);
		smsCodeFilter.setSmsCodeFailureHandler(smsCodeFailureHandler);
		smsCodeFilter.afterPropertiesSet();

		http.addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class);

		http.authorizeRequests()
			/*.antMatchers("/users/**", "/menus/**", "/roles/**").hasRole("ADMIN")*/
			.antMatchers("/code/sms").permitAll()
			.antMatchers("/authentication/mobile").permitAll()
			.antMatchers("/authentication/weixin").permitAll()
			.antMatchers("/**/*.png","/**/*.jpg").permitAll()
			.anyRequest()
			.authenticated();
		http.csrf().disable();
		/*http.apply(smsCodeAutenticationSecurityConfig);*/
		
	}

}
