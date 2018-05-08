package com.wh.weiguang.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wh.weiguang.model.me.InviteRelationEntity;

@Mapper
public interface InviteRelationDao {

	public void insert(InviteRelationEntity inviteRelationEntity);

	public InviteRelationEntity getInviteidByInvitedid(@Param("invitedid") int invitedid);

	public void setSign(InviteRelationEntity inviteRelationEntity);


}
