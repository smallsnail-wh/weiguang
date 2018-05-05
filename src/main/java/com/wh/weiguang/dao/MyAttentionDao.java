package com.wh.weiguang.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wh.weiguang.model.PageEntity;
import com.wh.weiguang.model.me.MyAttentionEntity;
import com.wh.weiguang.model.me.MyAttentionModel;

@Mapper
public interface MyAttentionDao {

	public List<MyAttentionModel> getAttentionsByUserid(@Param("userid") int userid,@Param("pageEntity") PageEntity pageEntity);

	public void insertAttention(MyAttentionEntity myAttentionEntity);

	public void deleteAttention(MyAttentionEntity myAttentionEntity);

}
