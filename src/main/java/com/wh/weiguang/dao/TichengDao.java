package com.wh.weiguang.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wh.weiguang.model.sys.TichengEntity;

@Mapper
public interface TichengDao {

	public TichengEntity getTicheng(@Param("form")int form);

	public void updateTicheng(TichengEntity tichengEntity);

}
