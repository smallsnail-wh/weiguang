package com.wh.weiguang.service.sys;

import com.wh.weiguang.model.sys.TichengEntity;

public interface TichengService {

	public TichengEntity getTicheng(int form);

	public void updateTicheng(TichengEntity tichengEntity);

}
