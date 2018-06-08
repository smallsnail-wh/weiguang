package com.wh.weiguang.service.sys.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.wh.weiguang.dao.InviteRelationDao;
import com.wh.weiguang.dao.RelationDao;
import com.wh.weiguang.dao.TradingFlowDao;
import com.wh.weiguang.dao.UserDao;
import com.wh.weiguang.dao.UserDetailDao;
import com.wh.weiguang.dao.WeixinUserDao;
import com.wh.weiguang.login.LoginFailureExcepiton;
import com.wh.weiguang.login.authentication.weixin.WeixinAuthentication;
import com.wh.weiguang.model.me.InviteRelationEntity;
import com.wh.weiguang.model.me.TradingFlowEntity;
import com.wh.weiguang.model.me.UserLevelEntity;
import com.wh.weiguang.model.sys.UserDetailEntity;
import com.wh.weiguang.model.sys.UserDetailModel;
import com.wh.weiguang.model.sys.UserEntity;
import com.wh.weiguang.model.sys.UserInfoModel;
import com.wh.weiguang.model.sys.WeixinUserInfo;
import com.wh.weiguang.properties.MyProperties;
import com.wh.weiguang.service.me.UserLevelService;
import com.wh.weiguang.service.sys.UserService;
import com.wh.weiguang.util.DateUtil;
import com.wh.weiguang.util.ImageUtil;
import com.wh.weiguang.util.SecurityAuthenUtil;

@Service(value = "userServiceImpl")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private WeixinUserDao weixinUserDao;
	
	@Autowired
	private UserDetailDao userDetailDao;
	
	@Autowired
	private InviteRelationDao inviteRelationDao;

	@Autowired
	private TradingFlowDao tradingFlowDao;
	
	@Autowired
	private RelationDao relationDao;
	
	@Autowired
	private MyProperties myProperties;

	@Autowired
	private UserLevelService userLevelService;
	
	private RestTemplate template = new RestTemplate();
	
	@Override
	@Transactional
	public void bindPhone(String mobile) {
		int userid = SecurityAuthenUtil.getId();
		
		UserEntity userEntity = userDao.getUserEntityByMobile(mobile);
		if(userEntity == null) {
			userDao.updateUserMobile(mobile,userid);
			return ;
		}
		
		userDao.untie(userEntity.getId());
		if(userEntity.getWeixinId() != null || !"".equals(userEntity.getWeixinId())) {
			weixinUserDao.delete(userEntity.getWeixinId());
		}
		userDao.updateUserMobile(mobile,userid);
		addMoney(userid, userEntity.getMoney(), "与id为"+userEntity.getId()+"账户合并所得");
		
	}

	@Override
	@Transactional
	public void bindWeixin(String code) {
		int userid = SecurityAuthenUtil.getId();
		
		String url = WeixinAuthentication.WEIXIN_ACCESSS_TOKEN_URL + "?appid=" + WeixinAuthentication.WEIXIN_APPID + "&secret=" + WeixinAuthentication.WEIXIN_SECRET + "&code=" + code
				+ "&grant_type=" + WeixinAuthentication.WEIXIN_GRANT_TYPE;
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
				insertWeixinUser(weinxinToken,userid);
				userDao.updateUserWeixin(openId,userid);
				/*insertInfoAndUser(weinxinToken, inviteCode);*/
			} else {
				/*String.valueOf(weixinUserInfo.getUserid());*/
				UserEntity userEntity = userDao.getUserByWeixinId(openId);
				if(userEntity == null) {
					return;
				}
				
				userDao.untie(userEntity.getId());
				weixinUserDao.updateUserid(userEntity.getWeixinId(),userid);
				/*if(userEntity.getWeixinId() != null || !"".equals(userEntity.getWeixinId())) {
					weixinUserDao.delete(userEntity.getWeixinId());
				}*/
				userDao.updateUserWeixin(openId,userid);
				addMoney(userid, userEntity.getMoney(), "与id为"+userEntity.getId()+"账户合并所得");
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}
	
	/**
	 * 插入微信用户信息
	 * @param weinxinToken
	 * @param userid
	 * @throws JSONException 
	 */
	public void insertWeixinUser(JSONObject weinxinToken,int userid) throws JSONException {
		String url = WeixinAuthentication.WEIXIN_INFO_URL + "?access_token=" + weinxinToken.getString("access_token") + "&openid="
				+ weinxinToken.getString("openid");

		ResponseEntity<String> responseEntity = template.getForEntity(url, String.class);

		JSONObject userOnWeixin = new JSONObject(responseEntity.getBody().trim());

		if (userOnWeixin.getString("openid") == null) {
			throw new LoginFailureExcepiton(weinxinToken.toString());
		}

		WeixinUserInfo weixinUserInfo = new WeixinUserInfo();
		weixinUserInfo.setOpenid(userOnWeixin.getString("openid"));
		weixinUserInfo.setNickname(userOnWeixin.getString("nickname"));
		weixinUserInfo.setSex(Integer.valueOf(userOnWeixin.getString("sex")));
		weixinUserInfo.setProvince(userOnWeixin.getString("province"));
		weixinUserInfo.setCity(userOnWeixin.getString("city"));
		weixinUserInfo.setCountry(userOnWeixin.getString("country"));
		weixinUserInfo.setHeadimgurl(userOnWeixin.getString("headimgurl"));
		weixinUserInfo.setUnionid(userOnWeixin.getString("unionid"));
		weixinUserInfo.setUserid(userid);
		weixinUserDao.insertInfo(weixinUserInfo);

	}
	
	@Override
	public void inviteSuccess(String inviteCode, int invitedid) {
		inviteCode = inviteCode.trim();
		if (inviteCode == null || "".equals(inviteCode)) {
			return;
		}

		int inviteid = userDao.getIdByInvitecode(inviteCode);

		UserDetailEntity userDetailEntity = userDetailDao.getUserDetailByUserid(inviteid);
		if(userDetailEntity == null) {
			return;
		}
		userDetailEntity.setExtraVtimes(userDetailEntity.getExtraVtimes() + 20);
		userDetailEntity.setInvitationNumber(userDetailEntity.getInvitationNumber() + 1);

		List<UserLevelEntity> levels = userLevelService.userLevelAll();
		for (UserLevelEntity levelTemp : levels) {
			if (userDetailEntity.getInvitationNumber() >= levelTemp.getMin()
					&& userDetailEntity.getInvitationNumber() <= levelTemp.getMax()) {
				if(userDetailEntity.getLevel() != levelTemp.getSort()) {
					userDetailEntity.setLevel(levelTemp.getSort());
				}
			}
		}
		userDetailDao.update(userDetailEntity);
		
		InviteRelationEntity inviteRelationEntity = new InviteRelationEntity();
		inviteRelationEntity.setInviteid(inviteid);
		inviteRelationEntity.setInvitedid(invitedid);
		inviteRelationEntity.setTime(DateUtil.currentTimestamp());
		if(userDetailEntity.getCustomerType() == 1) {
			inviteRelationEntity.setSign(1);
		}else {
			inviteRelationEntity.setSign(0);
		}
		inviteRelationDao.insert(inviteRelationEntity);
		
	}

	@Override
	public boolean addMoney(int id, double money, String describe) {
		
		userDao.recharge(id, money);

		TradingFlowEntity tradingFlowEntity = new TradingFlowEntity();
		tradingFlowEntity.setUserid(id);
		tradingFlowEntity.setAmount(money);
		tradingFlowEntity.setSign(0);
		tradingFlowEntity.setTime(DateUtil.currentTimestamp());
		tradingFlowEntity.setDescribe(describe);
		tradingFlowDao.insert(tradingFlowEntity);

		return true;
	}
	
	@Override
	public boolean consume(int id, double money, String describe) {
		double currentMoney = userDao.getCurrentMoney(id);
		if (currentMoney < money) {
			return false;
		}
		userDao.consume(id, money);

		TradingFlowEntity tradingFlowEntity = new TradingFlowEntity();
		tradingFlowEntity.setUserid(id);
		tradingFlowEntity.setAmount(money);
		tradingFlowEntity.setSign(1);
		tradingFlowEntity.setTime(DateUtil.currentTimestamp());
		tradingFlowEntity.setDescribe(describe);
		tradingFlowDao.insert(tradingFlowEntity);

		return true;
	}

	@Override
	public int getUserExtraVtimes(int userid) {
		return userDao.getUserExtraVtimes(userid);
	}

	@Override
	public void insert(UserEntity userEntity) {
		userDao.insert(userEntity);
	}

	@Override
	public UserEntity updateUser(MultipartFile headimg, String name) {
		UserEntity userEntity = new UserEntity();
		String headimgurl = null;
		try {
			if (headimg != null) {
				headimgurl = ImageUtil.saveImg(headimg, myProperties.getPathsProperties().getImage() + "/headportrait");
				headimgurl = myProperties.getPathsProperties().getDomainName() + "/headportrait/" + headimgurl;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		userEntity.setHeadimgurl(headimgurl);

		userEntity.setId(SecurityAuthenUtil.getId());
		userEntity.setName(name);

		userDao.update(userEntity);

		return userEntity;
	}

	@Override
	public UserDetailModel getDetailView(int id) {
		return userDao.getDetailView(id);
	}

	@Override
	public UserInfoModel getUserInfoById(int id) {
		return userDao.getUserInfoById(id);
	}

	@Override
	public void del(UserEntity userEntity) {
		userDao.del(userEntity);
	}

	@Override
	public UserEntity getUserEntityByLoginName(String loginName) {
		return userDao.getUserEntityByLoginName(loginName);
	}

	@Override
	public List<UserInfoModel> usersList(String loginName, int pageSize, int start) {
		return userDao.usersList(loginName, pageSize, start);
	}

	@Override
	public Integer usersSize(String loginName, int pageSize, int start) {
		return userDao.usersSize(loginName, pageSize, start);
	}
	
	@Override
	public List<UserEntity> adminusersList(String loginName, int pageSize, int start) {
		return userDao.adminusersList(loginName, pageSize, start);
	}

	@Override
	public Integer adminusersSize(String loginName, int pageSize, int start) {
		return userDao.adminusersSize(loginName, pageSize, start);
	}


	@Override
	public void insertUser(Map<String, String> userMap) {
		/*
		 * String password = null; try { password =
		 * MD5Util.encrypt(userEntity.getPassword()); userEntity.setPassword(password);
		 * } catch (NoSuchAlgorithmException e) { e.printStackTrace(); }
		 */
		// userEntity.setPassword(new
		// Md5PasswordEncoder().encodePassword(userEntity.getPassword(), null));
		/*
		 * userEntity.setPassword("{bcrypt}"+new
		 * BCryptPasswordEncoder().encode(userEntity.getPassword()));
		 */
		UserEntity userEntity = new UserEntity();
		userEntity = new UserEntity();
		userEntity.setMobile(userMap.get("mobile"));
		userEntity.setName(userMap.get("name"));
		userEntity.setPassword(userMap.get("password"));
		userEntity.setInviteCode(UUID.randomUUID().toString());
		userEntity.setCreateTime(DateUtil.currentTimestamp());
		/*userEntity.setLevel(0);*/
		
		userDao.insertUser(userEntity);
		
		UserDetailEntity userDetailEntity = new UserDetailEntity();
		userDetailEntity.setUserid(userEntity.getId());
		userDetailEntity.setCustomerType(Integer.valueOf(userMap.get("customerType")));
		userDetailDao.insert(userDetailEntity);
		
		if("3".equals(userMap.get("customerType"))) {
			relationDao.insertRelation(userEntity.getId(),1);
		}
	}

	@Override
	public void updateUser(UserEntity userEntity) {
		// userEntity.setPassword(new
		// Md5PasswordEncoder().encodePassword(userEntity.getPassword(), null));
		/*
		 * userEntity.setPassword("{bcrypt}"+new
		 * BCryptPasswordEncoder().encode(userEntity.getPassword()));
		 */
		userDao.updateUser(userEntity);
	}

	@Override
	@Transactional
	public void deleteUsers(List<String> groupId) {
		
		userDao.deleteUsers(groupId);
		userDetailDao.deleteByUserid(groupId);
		weixinUserDao.deleteByUserid(groupId);
	}
	
	@Override
	@Transactional
	public void deleteAdminusers(List<String> groupId) {
		for(String userid : groupId) {
			relationDao.delById(Integer.valueOf(userid));
		}
		userDao.deleteUsers(groupId);
		userDetailDao.deleteByUserid(groupId);
		weixinUserDao.deleteByUserid(groupId);
	}

	@Override
	public Integer getCount1() {
		
		return userDao.getCount1();
	}

	@Override
	public Integer getCount2(String time) {
		return userDao.getCount2(DateUtil.monthFirstday(time),DateUtil.monthLastday(time));
	}

	@Override
	public Integer getCount3(String time) {
		return userDao.getCount3(DateUtil.daystart(time),DateUtil.dayend(time));
	}

	@Override
	public Integer getCount4(String time) {
		return userDao.getCount4(DateUtil.monthFirstday(time),DateUtil.monthLastday(time));
	}

	@Override
	public Integer getCount5(String time) {
		return userDao.getCount5(DateUtil.daystart(time),DateUtil.dayend(time));
	}

	@Override
	public Integer getCount6() {
		return userDao.getCount6();
	}

	@Override
	public Integer getCount7(String time) {
		return userDao.getCount7(DateUtil.monthFirstday(time),DateUtil.monthLastday(time));
	}

	@Override
	public Integer getCount8(String time) {
		return userDao.getCount8(DateUtil.daystart(time),DateUtil.dayend(time));
	}

	@Override
	public Integer getCount9() {
		return userDao.getCount9();
	}

	@Override
	public Integer getCount10(String time) {
		return userDao.getCount10(DateUtil.monthFirstday(time),DateUtil.monthLastday(time));
	}

	@Override
	public Integer getCount11(String time) {
		return userDao.getCount11(DateUtil.daystart(time),DateUtil.dayend(time));
	}

	@Override
	public UserDetailModel getDetailView(String loginName, String mobile) {
		if (loginName == null || "".equals(loginName.trim())) {
			if(mobile == null || "".equals(mobile.trim())) {
				return null;
			}
		}else if(mobile == null || "".equals(mobile.trim())) {
			if(loginName == null || "".equals(loginName.trim())) {
				return null;
			}
		}
		return userDao.getDetByNameOrMobile(loginName,mobile);
	}

	@Override
	public List<UserInfoModel> usersTatiList(int type, int pageSize, int start, String time) {
		if(type == 1) {
			return userDao.getUserInfo1(pageSize,start);
		}else if(type == 2) {
			return userDao.getUserInfo2(pageSize,start,DateUtil.monthFirstday(time),DateUtil.monthLastday(time));
		}else if(type == 3) {
			return userDao.getUserInfo3(pageSize,start,DateUtil.daystart(time),DateUtil.dayend(time));
		}else if(type == 4) {
			return userDao.getUserInfo4(pageSize,start,DateUtil.monthFirstday(time),DateUtil.monthLastday(time));
		}else if(type == 5) {
			return userDao.getUserInfo5(pageSize,start,DateUtil.daystart(time),DateUtil.dayend(time));
		}
		return null;
	}

	@Override
	public Integer usersTatiSize(int type, String time) {
		if(type == 1) {
			return userDao.getInfoSize1();
		}else if(type == 2) {
			return userDao.getInfoSize2(DateUtil.monthFirstday(time),DateUtil.monthLastday(time));
		}else if(type == 3) {
			return userDao.getInfoSize3(DateUtil.daystart(time),DateUtil.dayend(time));
		}else if(type == 4) {
			return userDao.getInfoSize4(DateUtil.monthFirstday(time),DateUtil.monthLastday(time));
		}else if(type == 5) {
			return userDao.getInfoSize5(DateUtil.daystart(time),DateUtil.dayend(time));
		}
		return null;
	}

	@Override
	public List<UserInfoModel> publishersList(int type, int pageSize, int start, String time) {
		if(type == 1) {
			return userDao.getPublishers1(pageSize,start);
		}else if(type == 2) {
			return userDao.getPublishers2(pageSize,start,DateUtil.monthFirstday(time),DateUtil.monthLastday(time));
		}else if(type == 3) {
			return userDao.getPublishers3(pageSize,start,DateUtil.daystart(time),DateUtil.dayend(time));
		}
		return null;
	}

	@Override
	public Integer publishersSize(int type, String time) {
		if(type == 1) {
			return userDao.getPublishersSize1();
		}else if(type == 2) {
			return userDao.getPublishersSize2(DateUtil.monthFirstday(time),DateUtil.monthLastday(time));
		}else if(type == 3) {
			return userDao.getPublishersSize3(DateUtil.daystart(time),DateUtil.dayend(time));
		}
		return null;
	}

	@Override
	public List<UserInfoModel> ordinaryUsersList(int type, int pageSize, int start, String time) {
		if(type == 1) {
			return userDao.getOrdiUsers1(pageSize,start);
		}else if(type == 2) {
			return userDao.getOrdiUsers2(pageSize,start,DateUtil.monthFirstday(time),DateUtil.monthLastday(time));
		}else if(type == 3) {
			return userDao.getOrdiUsers3(pageSize,start,DateUtil.daystart(time),DateUtil.dayend(time));
		}
		return null;
	}

	@Override
	public Integer ordinaryUsersSize(int type, String time) {
		if(type == 1) {
			return userDao.getOrdiUsersSize1();
		}else if(type == 2) {
			return userDao.getOrdiUsersSize2(DateUtil.monthFirstday(time),DateUtil.monthLastday(time));
		}else if(type == 3) {
			return userDao.getOrdiUsersSize3(DateUtil.daystart(time),DateUtil.dayend(time));
		}
		return null;
	}

	@Override
	public List<UserInfoModel> salesmenList(int type, int pageSize, int start, String time) {
		if(type == 1) {
			return userDao.getSalesmen1(pageSize,start);
		}else if(type == 2) {
			return userDao.getSalesmen2(pageSize,start,DateUtil.monthFirstday(time),DateUtil.monthLastday(time));
		}else if(type == 3) {
			return userDao.getSalesmen3(pageSize,start,DateUtil.daystart(time),DateUtil.dayend(time));
		}
		return null;
	}

	@Override
	public Integer salesmenSize(int type, String time) {
		if(type == 1) {
			return userDao.getSalesmenSize1();
		}else if(type == 2) {
			return userDao.getSalesmenSize2(DateUtil.monthFirstday(time),DateUtil.monthLastday(time));
		}else if(type == 3) {
			return userDao.getSalesmenSize3(DateUtil.daystart(time),DateUtil.dayend(time));
		}
		return null;
	}

}
