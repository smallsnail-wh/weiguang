package com.wh.weiguang.service.sys;

import com.wh.weiguang.model.sys.RechargeActivityEntity;

public interface RechargeActivityuService {

	public RechargeActivityEntity getActivity();

	public void changeForm(int form);

	public void activityUpdate(RechargeActivityEntity rechargeActivityEntity);
	
}
