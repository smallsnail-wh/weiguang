package com.wh.weiguang.service.sys.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wh.weiguang.dao.UserDao;
import com.wh.weiguang.model.sys.UserEntity;
import com.wh.weiguang.properties.MyProperties;
import com.wh.weiguang.service.sys.UserService;
import com.wh.weiguang.util.ImageUtil;
import com.wh.weiguang.util.SecurityAuthenUtil;

@Service(value = "userServiceImpl")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private MyProperties myProperties;
	
	@Override
	public void insert(UserEntity userEntity) {
		userDao.insert(userEntity);
	}
	
	@Override
	public UserEntity updateUser(MultipartFile headimg, String name) {
		UserEntity userEntity = new UserEntity();
		String headimgurl = null;
		try {
			if(headimg != null) {
				headimgurl = ImageUtil.saveImg(headimg, myProperties.getPathsProperties().getImage()+"/headportrait");
				headimgurl = myProperties.getPathsProperties().getDomainName()+"/headportrait/"+headimgurl;
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
	public void del(UserEntity userEntity) {
		userDao.del(userEntity);
	}

	@Override
	public UserEntity getUserEntityByLoginName(String loginName) {
		return userDao.getUserEntityByLoginName(loginName);
	}

	@Override
	public List<UserEntity> usersList(String loginName, int pageSize, int start) {
		return userDao.usersList( loginName,  pageSize,  start);
	}

	@Override
	public Integer usersSize(String loginName, int pageSize, int start) {
		return userDao.usersSize(loginName, pageSize, start);
	}

	@Override
	public void insertUser(UserEntity userEntity) {
		/*String password = null;
		try {
			password = MD5Util.encrypt(userEntity.getPassword());
			userEntity.setPassword(password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}*/
		//userEntity.setPassword(new Md5PasswordEncoder().encodePassword(userEntity.getPassword(), null));
		/*userEntity.setPassword("{bcrypt}"+new BCryptPasswordEncoder().encode(userEntity.getPassword()));*/
		userDao.insertUser(userEntity);
	}

	@Override
	public void updateUser(UserEntity userEntity) {
		//userEntity.setPassword(new Md5PasswordEncoder().encodePassword(userEntity.getPassword(), null));
		/*userEntity.setPassword("{bcrypt}"+new BCryptPasswordEncoder().encode(userEntity.getPassword()));*/
		userDao.updateUser(userEntity);
	}

	@Override
	public void deleteUsers(List<String> groupId) {
		userDao.deleteUsers(groupId);
	}

}
