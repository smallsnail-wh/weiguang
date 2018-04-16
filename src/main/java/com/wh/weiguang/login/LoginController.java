package com.wh.weiguang.login;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wh.weiguang.login.authentication.MyAuthentication;
import com.wh.weiguang.login.authentication.MyAuthenticationToken;

@RestController
public class LoginController {

	@Autowired
	private LoginSuccessHandler loginSuccessHandler;
	
	@Resource(name="weixinAuthentication")
	private MyAuthentication weixinAuthentication;
	
	@Resource(name="smsCodeAuthentication")
	private MyAuthentication smsCodeAuthentication;

	public static final String WEIXIN_CODE = "code";
	
	public static final String WEIGUANG_FORM_MOBILE_KEY = "mobile";

	private String mobileParameter = WEIGUANG_FORM_MOBILE_KEY;

	@PostMapping("/authentication/mobile")
	public OAuth2AccessToken loginForSms(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String mobile = request.getParameter(mobileParameter);

		if (mobile == null) {
			mobile = "";
		}

		mobile = mobile.trim();

		String id = smsCodeAuthentication.getUserId(mobile);
		
		MyAuthenticationToken authRequest = new MyAuthenticationToken(id);

		authRequest.setDetails(new OAuth2AuthenticationDetails(request));

		OAuth2AccessToken oAuth2AccessToken = loginSuccessHandler.getAccessToken(request, response, authRequest);

		return oAuth2AccessToken;
	}
	
	@PostMapping("/authentication/weixin")
	public OAuth2AccessToken loginFo(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String code = request.getParameter(WEIXIN_CODE);

		if (code == null) {
			code = "";
		}

		code = code.trim();
		
		String id = weixinAuthentication.getUserId(code);
		

		MyAuthenticationToken authRequest = new MyAuthenticationToken(id);

		authRequest.setDetails(new OAuth2AuthenticationDetails(request));

		OAuth2AccessToken oAuth2AccessToken = loginSuccessHandler.getAccessToken(request, response, authRequest);

		return oAuth2AccessToken;
	}

}
