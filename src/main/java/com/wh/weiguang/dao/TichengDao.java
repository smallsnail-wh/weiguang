package com.wh.weiguang.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wh.weiguang.model.sys.TichengEntity;
import com.wh.weiguang.model.sys.TichengModel;

@Mapper
public interface TichengDao {

	public TichengEntity getTicheng(@Param("form")int form);

	public void updateTicheng(TichengEntity tichengEntity);

	public List<TichengModel> getTichengList1(@Param("pageSize") int pageSize, @Param("start") int start,
			@Param("timeStart") String timeStart, @Param("timeEnd") String timeEnd);

	public Integer getTichengSize1();

	public List<TichengModel> getTichengList2(@Param("pageSize") int pageSize, @Param("start") int start);

	public Integer getTichengSize2();

}
