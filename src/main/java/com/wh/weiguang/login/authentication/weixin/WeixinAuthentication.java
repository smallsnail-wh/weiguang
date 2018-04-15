package com.wh.weiguang.login.authentication.weixin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wh.weiguang.login.authentication.MyAuthentication;

@Service("weixinAuthentication")
public class WeixinAuthentication implements MyAuthentication {

	Logger log = LoggerFactory.getLogger(getClass());
	
	private RestTemplate template = new RestTemplate();
	
	private final static String WEIXIN_ACCESSS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";
	
	/*没确定*/
	private final static String WEIXIN_APPID = "appid";
	
	/*没确定*/
	private final static String WEIXIN_SECRET = "secret";
	
	private final static String WEIXIN_GRANT_TYPE = "authorization_code";
	
	@Override
	public String getUserId(String code) {
		
		String url = WEIXIN_ACCESSS_TOKEN_URL+"?appid="+WEIXIN_APPID+"&secret="+WEIXIN_SECRET+"&code="+code+"&grant_type="+WEIXIN_GRANT_TYPE;
		ResponseEntity<String> responseEntity = template.getForEntity(url, String.class);
		String reslut = responseEntity.getBody();
		
		return null;
	}

}
