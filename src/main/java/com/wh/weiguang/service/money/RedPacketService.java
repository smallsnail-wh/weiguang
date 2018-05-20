package com.wh.weiguang.service.money;

import java.util.List;

import com.wh.weiguang.model.advertise.AdvDetailModel;
import com.wh.weiguang.model.money.RedPacketDetailEntity;
import com.wh.weiguang.model.money.RedPacketDetailModel;

public interface RedPacketService {

	/**
	 * 抢红包
	 * @param advid
	 * @return
	 */
	public RedPacketDetailEntity grabRedPacket(int advid);

	/**
	 * 红包明细
	 * @param advid
	 * @return
	 */
	public List<RedPacketDetailModel> getDetailByAdvid(int advid);

	/**
	 * 第二红包
	 * @param advid
	 * @return
	 */
	public RedPacketDetailEntity grabSecondRedPacket(int advid);

	/**
	 * 是否有第二红包（0：有，1：无）
	 * @param advid
	 * @return
	 */
	public int haveSecondRedPacket(int advid);

	/**
	 * 抢红包前的准备
	 * @param advid
	 * @return
	 */
	public List<AdvDetailModel> readyGrabRedPacket(int advid);

	public Double getCount1();

	public Double getCount2(String time);

	public Double getCount3(String time);

}
