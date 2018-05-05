package com.wh.weiguang.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wh.weiguang.model.PageEntity;
import com.wh.weiguang.model.advertise.AdvCommentModel;
import com.wh.weiguang.model.me.AdvertisementCommentEntity;

@Mapper
public interface AdvertisementCommentDao {

	public Integer getCommentAmountByAdvid(@Param("advid") int advid);

	public void insertAdvComment(AdvertisementCommentEntity advertisementCommentEntity);

	public void addAdvCommentPop(@Param("id") int id);

	public List<AdvCommentModel> getAllAdvComment(@Param("advid") int advid,
			@Param("pageEntity") PageEntity pageEntity);

	public List<AdvCommentModel> getAdvCommentByParentid(@Param("parentid") int parentid,
			@Param("pageEntity") PageEntity pageEntity);
}
