package com.wh.weiguang.controller.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wh.weiguang.model.sys.LeaveMessageEntity;
import com.wh.weiguang.model.sys.PageResult;
import com.wh.weiguang.service.sys.LeaveMessageService;

import io.swagger.annotations.ApiOperation;

@RestController
public class LeaveMessageController {

	@Autowired
	private LeaveMessageService leaveMessageService;
	
	@PostMapping("/leavemess")
	@ApiOperation("发送留言")
	public LeaveMessageEntity entityInster(@RequestBody LeaveMessageEntity leaveMessageEntity) {
		
		leaveMessageService.insertEntity(leaveMessageEntity);
		
		return leaveMessageEntity;
	}
	
	@GetMapping("/admin/leavemess")
	public PageResult allEntityGet(int pageSize, int page){
		PageResult pageResult = new PageResult();
		pageResult.setData(leaveMessageService.entityList(pageSize, page * pageSize));
		pageResult.setTotalCount(leaveMessageService.entitySize());
		return pageResult;
	}
	
}
