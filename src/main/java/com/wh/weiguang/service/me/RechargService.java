package com.wh.weiguang.service.me;

import java.util.List;

import com.wh.weiguang.model.me.RechargeRecordEntity;
import com.wh.weiguang.model.me.RechargeRecordModel;

public interface RechargService {

	public void orderPaySucc(String id,String orderNumber,String amount);
	
	public void orderCreate(RechargeRecordEntity rechargeRecordEntity) ;
	
	public void zhifubaoRecharge(RechargeRecordEntity rechargeRecordEntity);

	public void weixinRecharge(RechargeRecordEntity rechargeRecordEntity);

	public Double getCount1();

	public Double getCount2(String time);

	public Double getCount3(String time);

	public List<RechargeRecordModel> rechargesList(int type, int pageSize, int start, String time);

	public Integer rechargesSize(int type, String time);

}
