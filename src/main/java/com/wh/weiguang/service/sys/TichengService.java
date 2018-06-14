package com.wh.weiguang.service.sys;

import java.util.List;

import com.wh.weiguang.model.sys.TichengEntity;
import com.wh.weiguang.model.sys.TichengModel;

public interface TichengService {

	public TichengEntity getTicheng(int form);

	public void updateTicheng(TichengEntity tichengEntity);

	public List<TichengModel> tichengList1(int pageSize, int start, String time);

	public Integer tichengSize1(String time);

	public List<TichengModel> tichengList2(int pageSize, int start);

	public Integer tichengSize2();

}
