package com.wh.weiguang.service.me;

import java.util.List;

import com.wh.weiguang.model.me.TradingFlowEntity;

public interface TradingFlowService {

	List<TradingFlowEntity> getListByUserid(int id);

}
