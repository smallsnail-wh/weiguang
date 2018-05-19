package com.wh.weiguang.login.authentication.weixin;

import java.io.IOException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wh.weiguang.dao.UserDao;
import com.wh.weiguang.dao.UserDetailDao;
import com.wh.weiguang.dao.WeixinUserDao;
import com.wh.weiguang.login.LoginFailureExcepiton;
import com.wh.weiguang.login.authentication.MyAuthentication;
import com.wh.weiguang.model.sys.UserDetailEntity;
import com.wh.weiguang.model.sys.UserEntity;
import com.wh.weiguang.model.sys.WeixinUserInfo;
import com.wh.weiguang.properties.MyProperties;
import com.wh.weiguang.service.sys.UserService;
import com.wh.weiguang.util.DateUtil;
import com.wh.weiguang.util.ImageUtil;

@Service("weixinAuthentication")
public class WeixinAuthentication implements MyAuthentication {

	Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private MyProperties myProperties;

	@Autowired
	private WeixinUserDao weixinUserDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserDetailDao userDetailDao;

	@Autowired
	private UserService userService;

	private RestTemplate template = new RestTemplate();

	public final static String WEIXIN_ACCESSS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";

	public final static String WEIXIN_INFO_URL = "https://api.weixin.qq.com/sns/userinfo";

	/* 没确定 */
	public final static String WEIXIN_APPID = "appid";

	/* 没确定 */
	public final static String WEIXIN_SECRET = "secret";

	public final static String WEIXIN_GRANT_TYPE = "authorization_code";

	@Override
	public String getUserId(String code, String inviteCode) {

		// 测试代码

		/*
		 * WeixinUserInfo weixinUserInfo = null;
		 * 
		 * try {
		 * 
		 * String s =
		 * "{\"openid\":\"test001\",\"nickname\":\"test001\",\"sex\":\"0\",\"province\":\"上海\",\"city\":\"上海\",\"country\":\"中国\",\"headimgurl\":\"xxxxx\",\"unionid\":\"1001\"}";
		 * 
		 * JSONObject weinxinToken = new JSONObject(s);
		 * 
		 * String openId = weinxinToken.getString("openid");
		 * 
		 * if (openId == null) { throw new
		 * LoginFailureExcepiton(weinxinToken.toString()); }
		 * 
		 * weixinUserInfo = weixinUserDao.getInfoByOpenid(openId);
		 * 
		 * if (weixinUserInfo == null) { return insertInfoAndUser(weinxinToken); } else
		 * { return String.valueOf(weixinUserInfo.getUserid()); }
		 * 
		 * } catch (Exception e) { e.printStackTrace(); }
		 */

		// 生产代码
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
				return insertInfoAndUser(weinxinToken, inviteCode);
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
	 * @param inviteCode
	 * @return
	 * @throws JSONException
	 * @throws IOException
	 */
	private String insertInfoAndUser(JSONObject weinxinToken, String inviteCode) throws JSONException, IOException {

		// 测试代码
		/*
		 * JSONObject userOnWeixin = weinxinToken;
		 * 
		 * if (userOnWeixin.getString("openid") == null) { throw new
		 * LoginFailureExcepiton(weinxinToken.toString()); }
		 * 
		 * log.info("用户:" + userOnWeixin.getString("openid") + "不存在！");
		 * 
		 * log.info("新建用户:" + userOnWeixin.getString("openid"));
		 * 
		 * UserEntity userEntity = new UserEntity(); userEntity.setLevel(0);
		 * userEntity.setWeixinId(userOnWeixin.getString("openid"));
		 * userDao.insertByWeixin(userEntity);
		 * 
		 * WeixinUserInfo weixinUserInfo = new WeixinUserInfo();
		 * weixinUserInfo.setOpenid(userOnWeixin.getString("openid"));
		 * weixinUserInfo.setNickname(userOnWeixin.getString("nickname"));
		 * weixinUserInfo.setSex(Integer.valueOf(userOnWeixin.getString("sex")));
		 * weixinUserInfo.setProvince(userOnWeixin.getString("province"));
		 * weixinUserInfo.setCity(userOnWeixin.getString("city"));
		 * weixinUserInfo.setCountry(userOnWeixin.getString("country"));
		 * weixinUserInfo.setHeadimgurl(userOnWeixin.getString("headimgurl"));
		 * weixinUserInfo.setUnionid(userOnWeixin.getString("unionid"));
		 * weixinUserInfo.setUserid(userEntity.getId());
		 * weixinUserDao.insertInfo(weixinUserInfo);
		 */

		// 生产代码
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
		userEntity.setInviteCode(UUID.randomUUID().toString());
		userEntity.setCreateTime(DateUtil.currentTimestamp());
		/* userEntity.setLevel(0); */
		userEntity.setWeixinId(userOnWeixin.getString("openid"));

		/* 下载微信头像到本地 */
		String headimgurl = ImageUtil.saveImg(userOnWeixin.getString("headimgurl"),
				myProperties.getPathsProperties().getImage() + "/headportrait");
		headimgurl = myProperties.getPathsProperties().getDomainName() + "/headportrait/" + headimgurl;
		userEntity.setHeadimgurl(headimgurl);

		userDao.insertByWeixin(userEntity);

		UserDetailEntity userDetailEntity = new UserDetailEntity();
		userDetailEntity.setUserid(userEntity.getId());
		userDetailEntity.setCustomerType(0);
		userDetailDao.insert(userDetailEntity);

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

		if (inviteCode != null && !"".equals(inviteCode)) {
			userService.inviteSuccess(inviteCode,userEntity.getId());
		}

		return String.valueOf(userEntity.getId());
	}

	public void bindWeixin(int userid, String code) {
		
	}

}
