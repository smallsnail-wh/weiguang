package com.wh.weiguang.service.sys.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wh.weiguang.dao.InviteRelationDao;
import com.wh.weiguang.dao.TradingFlowDao;
import com.wh.weiguang.dao.UserDao;
import com.wh.weiguang.dao.UserDetailDao;
import com.wh.weiguang.model.me.InviteRelationEntity;
import com.wh.weiguang.model.me.TradingFlowEntity;
import com.wh.weiguang.model.me.UserLevelEntity;
import com.wh.weiguang.model.sys.UserDetailEntity;
import com.wh.weiguang.model.sys.UserDetailModel;
import com.wh.weiguang.model.sys.UserEntity;
import com.wh.weiguang.model.sys.UserInfoModel;
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
	private UserDetailDao userDetailDao;
	
	@Autowired
	private InviteRelationDao inviteRelationDao;

	@Autowired
	private TradingFlowDao tradingFlowDao;
	
	@Autowired
	private MyProperties myProperties;

	@Autowired
	private UserLevelService userLevelService;

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
	public List<UserEntity> usersList(String loginName, int pageSize, int start) {
		return userDao.usersList(loginName, pageSize, start);
	}

	@Override
	public Integer usersSize(String loginName, int pageSize, int start) {
		return userDao.usersSize(loginName, pageSize, start);
	}

	@Override
	public void insertUser(UserEntity userEntity) {
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
		userDao.insertUser(userEntity);
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
	public void deleteUsers(List<String> groupId) {
		userDao.deleteUsers(groupId);
	}

}
