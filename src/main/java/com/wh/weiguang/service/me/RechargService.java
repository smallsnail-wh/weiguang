package com.wh.weiguang.service.me;

import com.wh.weiguang.model.me.RechargeRecordEntity;

public interface RechargService {

	public void zhifubaoRecharge(RechargeRecordEntity rechargeRecordEntity);

	public void weixinRecharge(RechargeRecordEntity rechargeRecordEntity);

	public Double getCount1();

	public Double getCount2(String time);

	public Double getCount3(String time);

}
