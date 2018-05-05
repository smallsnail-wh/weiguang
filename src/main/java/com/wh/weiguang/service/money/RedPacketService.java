package com.wh.weiguang.service.money;

import java.util.List;

import com.wh.weiguang.model.money.RedPacketDetailEntity;
import com.wh.weiguang.model.money.RedPacketDetailModel;

public interface RedPacketService {

	/**
	 * 抢红包
	 * @param advid
	 * @return
	 */
	RedPacketDetailEntity grabRedPacket(int advid);

	/**
	 * 红包明细
	 * @param advid
	 * @return
	 */
	List<RedPacketDetailModel> getDetailByAdvid(int advid);

}
