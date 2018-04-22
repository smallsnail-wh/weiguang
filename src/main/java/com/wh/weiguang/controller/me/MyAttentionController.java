package com.wh.weiguang.controller.me;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wh.weiguang.model.me.MyAttentionEntity;
import com.wh.weiguang.model.me.MyAttentionModel;
import com.wh.weiguang.service.me.MyAttentionService;
import com.wh.weiguang.util.SecurityAuthenUtil;

@RestController
public class MyAttentionController {

	@Autowired
	private MyAttentionService myAttentionService;
	
	@GetMapping("/user/attentions")
	public List<MyAttentionModel> myAttentionsGet() {
		return myAttentionService.getAttentionsByUserid(SecurityAuthenUtil.getId());
	}
	
	@PostMapping("/user/attentions")
	public MyAttentionEntity attentionsPost(@RequestBody MyAttentionEntity myAttentionEntity) {
		myAttentionEntity.setUserid(SecurityAuthenUtil.getId());
		myAttentionService.insertAttention(myAttentionEntity);
		return myAttentionEntity;
	}
	
	@DeleteMapping("/user/attentions")
	public MyAttentionEntity attentionsDelete(@RequestBody MyAttentionEntity myAttentionEntity) {
		myAttentionEntity.setUserid(SecurityAuthenUtil.getId());
		myAttentionService.deleteAttention(myAttentionEntity);
		return myAttentionEntity;
	}
	
}
