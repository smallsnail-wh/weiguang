package com.wh.weiguang.controller.money;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wh.weiguang.model.advertise.AdvDetailModel;
import com.wh.weiguang.model.money.RedPacketDetailEntity;
import com.wh.weiguang.model.money.RedPacketDetailModel;
import com.wh.weiguang.model.sys.PageResult;
import com.wh.weiguang.service.money.RedPacketService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
public class RedPacketController {

	@Autowired
	private RedPacketService redPacketService;

	@ApiOperation("抢红包前准备")
	@PutMapping("/redpacket/grab/ready")
	public List<AdvDetailModel> readyGrabRedPacket(@ApiParam(value = "广告id") @RequestParam("advid") int advid) {
		
		return redPacketService.readyGrabRedPacket(advid);
	}
	
	@ApiOperation("抢红包")
	@PutMapping("/redpacket/grab")
	public Map<String,Object> grabRedPacket(@ApiParam(value = "广告id") @RequestParam("advid") int advid) {
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("message", redPacketService.grabRedPacket(advid));
		resultMap.put("secondRedPacket", redPacketService.haveSecondRedPacket(advid));
		return resultMap;
	}
	
	@ApiOperation("第二红包")
	@PutMapping("/redpacket/grabsecond")
	public RedPacketDetailEntity grabSecondRedPacket(@ApiParam(value = "广告id") @RequestParam("advid") int advid) {
		return redPacketService.grabSecondRedPacket(advid);
	}

	@ApiOperation("红包明细")
	@GetMapping("/redpacket/detail")
	public List<RedPacketDetailModel> redPacketDetail(@ApiParam(value = "广告id") @RequestParam("advid") int advid) {
		return redPacketService.getDetailByAdvid(advid);
	}
	
	@GetMapping("/admin/receive")
	public PageResult receiverList(int type, int pageSize, int page,String time) {
		PageResult pageResult = new PageResult();
		pageResult.setData(redPacketService.receiveList(type, pageSize, page * pageSize,time));
		pageResult.setTotalCount(redPacketService.receiveSize(type,time));
		return pageResult;
	}
	
	/**
	 * 总领取金额
	 * 
	 * @return
	 */
	@GetMapping("/redpacket/count1")
	public Double getCount1() {

		return redPacketService.getCount1();
	}

	/**
	 * 月领取金额
	 * 
	 * @return
	 */
	@GetMapping("/redpacket/count2")
	public Double getCount2(@RequestParam("time") String time) {

		return redPacketService.getCount2(time);
	}

	/**
	 * 日领取金额
	 * 
	 * @return
	 */
	@GetMapping("/redpacket/count3")
	public Double getCount3(@RequestParam("time") String time) {

		return redPacketService.getCount3(time);
	}

}
