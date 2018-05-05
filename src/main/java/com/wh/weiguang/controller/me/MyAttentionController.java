package com.wh.weiguang.controller.me;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wh.weiguang.model.PageEntity;
import com.wh.weiguang.model.me.MyAttentionEntity;
import com.wh.weiguang.model.me.MyAttentionModel;
import com.wh.weiguang.service.me.MyAttentionService;
import com.wh.weiguang.util.SecurityAuthenUtil;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
public class MyAttentionController {

	@Autowired
	private MyAttentionService myAttentionService;

	@ApiOperation("我的关注")
	@GetMapping("/user/attentions")
	public List<MyAttentionModel> myAttentionsGet(
			@ApiParam(value = "分页的参数，每页多少数据") @RequestParam("pageSize") int pageSize,
			@ApiParam(value = "分页的参数，第几页,从0开始") @RequestParam("page") int page) {
		PageEntity pageEntity = new PageEntity(pageSize, page);
		return myAttentionService.getAttentionsByUserid(SecurityAuthenUtil.getId(),pageEntity);
	}

	@ApiOperation("关注")
	@PostMapping("/user/attentions")
	public MyAttentionEntity attentionsPost(@ApiParam(value = "关注人id") @RequestParam("attentionid") int attentionid) {
		MyAttentionEntity myAttentionEntity =  new MyAttentionEntity();
		myAttentionEntity.setAttentionid(attentionid);
		myAttentionEntity.setUserid(SecurityAuthenUtil.getId());
		myAttentionService.insertAttention(myAttentionEntity);
		return myAttentionEntity;
	}

	@ApiOperation("取消关注")
	@DeleteMapping("/user/attentions")
	public MyAttentionEntity attentionsDelete(@ApiParam(value = "关注人id") @RequestParam("attentionid") int attentionid) {
		MyAttentionEntity myAttentionEntity =  new MyAttentionEntity();
		myAttentionEntity.setAttentionid(attentionid);
		myAttentionEntity.setUserid(SecurityAuthenUtil.getId());
		myAttentionService.deleteAttention(myAttentionEntity);
		return myAttentionEntity;
	}

}
