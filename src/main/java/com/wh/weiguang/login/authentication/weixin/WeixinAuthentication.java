package com.wh.weiguang.login.authentication.weixin;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wh.weiguang.dao.UserDao;
import com.wh.weiguang.dao.WeixinUserDao;
import com.wh.weiguang.login.LoginFailureExcepiton;
import com.wh.weiguang.login.authentication.MyAuthentication;
import com.wh.weiguang.model.sys.UserEntity;
import com.wh.weiguang.model.sys.WeixinUserInfo;

@Service("weixinAuthentication")
public class WeixinAuthentication implements MyAuthentication {

	Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private WeixinUserDao weixinUserDao;
	
	@Autowired
	private UserDao userDao;

	private RestTemplate template = new RestTemplate();

	private final static String WEIXIN_ACCESSS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";

	private final static String WEIXIN_INFO_URL = "https://api.weixin.qq.com/sns/userinfo";

	/* 没确定 */
	private final static String WEIXIN_APPID = "appid";

	/* 没确定 */
	private final static String WEIXIN_SECRET = "secret";

	private final static String WEIXIN_GRANT_TYPE = "authorization_code";

	@Override
	public String getUserId(String code) {

		//测试代码

		/*WeixinUserInfo weixinUserInfo = null;

		try {

			String s = "{\"openid\":\"test001\",\"nickname\":\"test001\",\"sex\":\"0\",\"province\":\"上海\",\"city\":\"上海\",\"country\":\"中国\",\"headimgurl\":\"xxxxx\",\"unionid\":\"1001\"}";
			
			JSONObject weinxinToken = new JSONObject(s);

			String openId = weinxinToken.getString("openid");

			if (openId == null) {
				throw new LoginFailureExcepiton(weinxinToken.toString());
			}

			weixinUserInfo = weixinUserDao.getInfoByOpenid(openId);

			if (weixinUserInfo == null) {
				return insertInfoAndUser(weinxinToken);
			} else {
				return String.valueOf(weixinUserInfo.getUserid());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		
		//生产代码
		String url = WEIXIN_ACCESSS_TOKEN_URL + "?appid=" + WEIXIN_APPID + "&secret=" + WEIXIN_SECRET + "&code=" + code
				+ "&grant_type=" + WEIXIN_GRANT_TYPE;
		ResponseEntity<String> responseEntity = template.getForEntity(url, String.class);

		WeixinUserInfo weixinUserInfo = null;

		try {

			JSONObject weinxinToken = new JSONObject(responseEntity.getBody().trim());

			String openId = weinxinToken.getString("openid");

			if (openId == null) {
				throw new LoginFailureExcepiton(weinxinToken.toString());
			}

			weixinUserInfo = weixinUserDao.getInfoByOpenid(openId);

			if (weixinUserInfo == null) {
				return insertInfoAndUser(weinxinToken);
			} else {
				return String.valueOf(weixinUserInfo.getUserid());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * 向微信查询用户信息并导入数据库
	 * 
	 * @param weinxinToken
	 * @return
	 * @throws JSONException
	 */
	private String insertInfoAndUser(JSONObject weinxinToken) throws JSONException {
		
		//测试代码
		/*JSONObject userOnWeixin = weinxinToken;

		if (userOnWeixin.getString("openid") == null) {
			throw new LoginFailureExcepiton(weinxinToken.toString());
		}

		log.info("用户:" + userOnWeixin.getString("openid") + "不存在！");

		log.info("新建用户:" + userOnWeixin.getString("openid"));
		
		UserEntity userEntity = new UserEntity();
		userEntity.setLevel(0);
		userEntity.setWeixinId(userOnWeixin.getString("openid"));
		userDao.insertByWeixin(userEntity);

		WeixinUserInfo weixinUserInfo = new WeixinUserInfo();
		weixinUserInfo.setOpenid(userOnWeixin.getString("openid"));
		weixinUserInfo.setNickname(userOnWeixin.getString("nickname"));
		weixinUserInfo.setSex(Integer.valueOf(userOnWeixin.getString("sex")));
		weixinUserInfo.setProvince(userOnWeixin.getString("province"));
		weixinUserInfo.setCity(userOnWeixin.getString("city"));
		weixinUserInfo.setCountry(userOnWeixin.getString("country"));
		weixinUserInfo.setHeadimgurl(userOnWeixin.getString("headimgurl"));
		weixinUserInfo.setUnionid(userOnWeixin.getString("unionid"));
		weixinUserInfo.setUserid(userEntity.getId());
		weixinUserDao.insertInfo(weixinUserInfo);*/
		
		//生产代码
		String url = WEIXIN_INFO_URL + "?access_token=" + weinxinToken.getString("access_token") + "&openid="
				+ weinxinToken.getString("openid");

		ResponseEntity<String> responseEntity = template.getForEntity(url, String.class);

		JSONObject userOnWeixin = new JSONObject(responseEntity.getBody().trim());

		if (userOnWeixin.getString("openid") == null) {
			throw new LoginFailureExcepiton(weinxinToken.toString());
		}

		log.info("用户:" + userOnWeixin.getString("openid") + "不存在！");

		log.info("新建用户:" + userOnWeixin.getString("openid"));
		
		UserEntity userEntity = new UserEntity();
		userEntity.setLevel(0);
		userEntity.setWeixinId(userOnWeixin.getString("openid"));
		userDao.insertByWeixin(userEntity);

		WeixinUserInfo weixinUserInfo = new WeixinUserInfo();
		weixinUserInfo.setOpenid(userOnWeixin.getString("openid"));
		weixinUserInfo.setNickname(userOnWeixin.getString("nickname"));
		weixinUserInfo.setSex(Integer.valueOf(userOnWeixin.getString("sex")));
		weixinUserInfo.setProvince(userOnWeixin.getString("province"));
		weixinUserInfo.setCity(userOnWeixin.getString("city"));
		weixinUserInfo.setCountry(userOnWeixin.getString("country"));
		weixinUserInfo.setHeadimgurl(userOnWeixin.getString("headimgurl"));
		weixinUserInfo.setUnionid(userOnWeixin.getString("unionid"));
		weixinUserInfo.setUserid(userEntity.getId());
		weixinUserDao.insertInfo(weixinUserInfo);
		
		return String.valueOf(userEntity.getId());
	}

}
