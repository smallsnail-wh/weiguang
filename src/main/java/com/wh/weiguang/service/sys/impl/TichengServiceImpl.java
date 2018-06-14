package com.wh.weiguang.service.sys.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wh.weiguang.dao.TichengDao;
import com.wh.weiguang.model.sys.TichengEntity;
import com.wh.weiguang.model.sys.TichengModel;
import com.wh.weiguang.service.sys.TichengService;
import com.wh.weiguang.util.DateUtil;

@Service
public class TichengServiceImpl implements TichengService {

	@Autowired
	private TichengDao tichengDao;

	@Override
	public TichengEntity getTicheng(int form) {
		return tichengDao.getTicheng(form);
	}

	@Override
	public void updateTicheng(TichengEntity tichengEntity) {
		tichengDao.updateTicheng(tichengEntity);
	}

	@Override
	public List<TichengModel> tichengList1(int pageSize, int start, String time) {
		return tichengDao.getTichengList1(pageSize,start,DateUtil.monthFirstday(time),DateUtil.monthLastday(time));
	}

	@Override
	public Integer tichengSize1(String time) {
		return tichengDao.getTichengSize1();
	}

	@Override
	public List<TichengModel> tichengList2(int pageSize, int start) {
		List<TichengModel> list = tichengDao.getTichengList2(pageSize,start);
		return list;
	}

	@Override
	public Integer tichengSize2() {
		return tichengDao.getTichengSize2();
	}

}
