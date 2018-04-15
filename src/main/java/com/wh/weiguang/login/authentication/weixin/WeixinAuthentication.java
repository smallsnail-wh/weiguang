package com.wh.weiguang.login.authentication.weixin;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wh.weiguang.dao.WeixinUserDao;
import com.wh.weiguang.login.authentication.MyAuthentication;
import com.wh.weiguang.model.sys.WeixinUserInfo;

@Service("weixinAuthentication")
public class WeixinAuthentication implements MyAuthentication {

	Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private WeixinUserDao weixinUserDao;
	
	private RestTemplate template = new RestTemplate();
	
	private final static String WEIXIN_ACCESSS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";
	
	private final static String WEIXIN_INFO_URL = "https://api.weixin.qq.com/sns/userinfo";
	
	/*没确定*/
	private final static String WEIXIN_APPID = "appid";
	
	/*没确定*/
	private final static String WEIXIN_SECRET = "secret";
	
	private final static String WEIXIN_GRANT_TYPE = "authorization_code";
	
	@Override
	public String getUserId(String code) {
		
		String url = WEIXIN_ACCESSS_TOKEN_URL+"?appid="+WEIXIN_APPID+"&secret="+WEIXIN_SECRET+"&code="+code+"&grant_type="+WEIXIN_GRANT_TYPE;
		ResponseEntity<String> responseEntity = template.getForEntity(url, String.class);
		
		WeixinUserInfo weixinUserInfo = null;
		
		try {
			
			JSONObject weinxinToken = new JSONObject(responseEntity.getBody().trim());
			
			String openId = weinxinToken.getString("openid");
			
			weixinUserInfo = weixinUserDao.getInfoByOpenid(openId);
			
			if(weixinUserInfo == null) {
				return insertInfoAndUser(weinxinToken);
			}else {
				return String.valueOf(weixinUserInfo.getUserid());
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}

	private String insertInfoAndUser(JSONObject weinxinToken) {
		
		
		
		return null;
	}

}
