package com.wh.weiguang.login.authentication.sms;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wh.weiguang.dao.UserDao;
import com.wh.weiguang.dao.UserDetailDao;
import com.wh.weiguang.login.authentication.MyAuthentication;
import com.wh.weiguang.model.sys.UserDetailEntity;
import com.wh.weiguang.model.sys.UserEntity;
import com.wh.weiguang.service.sys.UserService;
import com.wh.weiguang.util.DateUtil;

@Service("smsCodeAuthentication")
public class SmsCodeAuthentication implements MyAuthentication {

	Logger log = LoggerFactory.getLogger(SmsCodeAuthentication.class);
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserDetailDao userDetailDao;
	
	@Autowired
	private UserService userService;
	
	@Override
	public String getUserId(String mobile, String inviteCode) {
		
		UserEntity userEntity = userDao.getUserEntityByMobile(mobile);
		
		if(userEntity == null) {
			/*throw new UsernameNotFoundException("用户:"+ mobile + "不存在！");*/
			log.info("用户:"+ mobile + "不存在！");
			log.info("新建用户:"+ mobile );
			
			userEntity = new UserEntity();
			userEntity.setMobile(mobile);
			userEntity.setInviteCode(UUID.randomUUID().toString());
			userEntity.setCreateTime(DateUtil.currentTimestamp());
			/*userEntity.setLevel(0);*/
			
			userDao.insert(userEntity);
			
			UserDetailEntity userDetailEntity = new UserDetailEntity();
			userDetailEntity.setUserid(userEntity.getId());
			userDetailEntity.setCustomerType(0);
			userDetailDao.insert(userDetailEntity);
			
			if (inviteCode != null && !"".equals(inviteCode)) {
				userService.inviteSuccess(inviteCode,userEntity.getId());
			}
			
			return String.valueOf(userEntity.getId());
			
		}
		
		return String.valueOf(userEntity.getId());
	}

}
