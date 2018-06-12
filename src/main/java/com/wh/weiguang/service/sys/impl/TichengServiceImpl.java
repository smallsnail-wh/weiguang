package com.wh.weiguang.service.sys.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wh.weiguang.dao.TichengDao;
import com.wh.weiguang.model.sys.TichengEntity;
import com.wh.weiguang.service.sys.TichengService;

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


}
