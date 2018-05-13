package com.wh.weiguang.controller.money;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wh.weiguang.model.advertise.AdvDetailModel;
import com.wh.weiguang.model.money.RedPacketDetailEntity;
import com.wh.weiguang.model.money.RedPacketDetailModel;
import com.wh.weiguang.service.money.RedPacketService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/redpacket")
public class RedPacketController {

	@Autowired
	private RedPacketService redPacketService;

	@ApiOperation("抢红包前准备")
	@PutMapping("/grab/ready")
	public List<AdvDetailModel> readyGrabRedPacket(@ApiParam(value = "广告id") @RequestParam("advid") int advid) {
		
		return redPacketService.readyGrabRedPacket(advid);
	}
	
	@ApiOperation("抢红包")
	@PutMapping("/grab")
	public Map<String,Object> grabRedPacket(@ApiParam(value = "广告id") @RequestParam("advid") int advid) {
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("message", redPacketService.grabRedPacket(advid));
		resultMap.put("secondRedPacket", redPacketService.haveSecondRedPacket(advid));
		return resultMap;
	}
	
	@ApiOperation("第二红包")
	@PutMapping("/grabsecond")
	public RedPacketDetailEntity grabSecondRedPacket(@ApiParam(value = "广告id") @RequestParam("advid") int advid) {
		return redPacketService.grabSecondRedPacket(advid);
	}

	@ApiOperation("红包明细")
	@GetMapping("/detail")
	public List<RedPacketDetailModel> redPacketDetail(@ApiParam(value = "广告id") @RequestParam("advid") int advid) {
		return redPacketService.getDetailByAdvid(advid);
	}

}
