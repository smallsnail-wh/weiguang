package com.wh.weiguang.service.me;

import java.util.List;

import com.wh.weiguang.model.PageEntity;
import com.wh.weiguang.model.me.MyAttentionEntity;
import com.wh.weiguang.model.me.MyAttentionModel;

public interface MyAttentionService {

	public List<MyAttentionModel> getAttentionsByUserid(int userid, PageEntity pageEntity);

	public void insertAttention(MyAttentionEntity myAttentionEntity);

	public void deleteAttention(MyAttentionEntity myAttentionEntity);
	
}
