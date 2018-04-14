package com.wh.weiguang.login.validate.sms;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.wh.weiguang.properties.MyProperties;

public class SmsCodeFilter extends OncePerRequestFilter {
	
	/*@Autowired
	private ValidateCodeFailureHandler validateCodeFailureHandler;
	
	@Autowired
	private ImageCodeDefaultProperties imageCodeDefaultProperties;*/
	
	private MyProperties myProperties;
	
	private SmsCodeFailureHandler smsCodeFailureHandler;
	
	private final static String MOBILE_KEY ="mobile";
	
	private StringRedisTemplate stringRedisTemplate;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String[] urls = myProperties.getSmsCodeProperties().getUrl().split(",");
		
		boolean flag = false;
		for(String url : urls) {
			if(url.equals(request.getServletPath().toString())) {
				flag = true;
			}
		}
		
		if(flag) {
			try {
				validate(request);
			} catch (SmsCodeException e) {
				smsCodeFailureHandler.handleValidateFilureException(request,response,e);
				return;
			}
		}
		
		filterChain.doFilter(request, response);
	}

	private void validate(HttpServletRequest request) throws ServletRequestBindingException, SmsCodeException {
		
		/*ImageCode codeInSession = (ImageCode) request.getSession().getAttribute(ValidateCodeController.SESSION_KEY);*/
		
		String mobile = ServletRequestUtils.getRequiredStringParameter(request, MOBILE_KEY);
		
		ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
		String codeInRedis = valueOperations.get(mobile);
		
		String codeInRequest = ServletRequestUtils.getRequiredStringParameter(request, "code");		
		
		if(codeInRequest == null) {
			throw new SmsCodeException("验证码不能为空");
		}
		
		if(codeInRedis == null) {
			throw new SmsCodeException("验证码不存在,或者验证码已过期");
		}
		
		if(!codeInRequest.equalsIgnoreCase(codeInRedis)) {
			throw new SmsCodeException("验证码不匹配");
		}
		System.out.println("验证成功");
	}

	public MyProperties getMyProperties() {
		return myProperties;
	}

	public void setMyProperties(MyProperties myProperties) {
		this.myProperties = myProperties;
	}

	public SmsCodeFailureHandler getSmsCodeFailureHandler() {
		return smsCodeFailureHandler;
	}

	public void setSmsCodeFailureHandler(SmsCodeFailureHandler smsCodeFailureHandler) {
		this.smsCodeFailureHandler = smsCodeFailureHandler;
	}

	public StringRedisTemplate getStringRedisTemplate() {
		return stringRedisTemplate;
	}

	public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
		this.stringRedisTemplate = stringRedisTemplate;
	}

}
