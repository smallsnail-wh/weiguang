package com.wh.weiguang.login.authentication.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wh.weiguang.dao.UserDao;
import com.wh.weiguang.login.authentication.MyAuthentication;
import com.wh.weiguang.model.sys.UserEntity;

@Service("smsCodeAuthentication")
public class SmsCodeAuthentication implements MyAuthentication {

	Logger log = LoggerFactory.getLogger(SmsCodeAuthentication.class);
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public String getUserId(String mobile) {
		
		UserEntity userEntity = userDao.getUserEntityByMobile(mobile);
		
		if(userEntity == null) {
			/*throw new UsernameNotFoundException("用户:"+ mobile + "不存在！");*/
			log.info("用户:"+ mobile + "不存在！");
			log.info("新建用户:"+ mobile );
			
			userEntity = new UserEntity();
			userEntity.setMobile(mobile);
			userEntity.setLevel(0);
			
			userDao.insert(userEntity);
			
			return String.valueOf(userEntity.getId());
			
		}
		
		return String.valueOf(userEntity.getId());
	}

}
