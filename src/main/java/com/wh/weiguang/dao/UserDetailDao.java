package com.wh.weiguang.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wh.weiguang.model.sys.UserDetailEntity;

@Mapper
public interface UserDetailDao {

	public void insert(@Param("userDetailEntity") UserDetailEntity userDetailEntity);

	public UserDetailEntity getUserDetailByUserid(@Param("userid")int userid);

	public void reduceTodayVtimes(@Param("userid")int userid);

	public void reduceExtraVtimes(@Param("userid")int userid);

	public void update(UserDetailEntity userDetailEntity);

	public void deleteByUserid(@Param("groupId") List<String> groupId);

	public void changeCustomerType(@Param("userid") int userid,@Param("type") int type);
	
}
