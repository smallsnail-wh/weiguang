package com.wh.weiguang.controller.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wh.weiguang.model.sys.PageResult;
import com.wh.weiguang.model.sys.TichengEntity;
import com.wh.weiguang.service.sys.TichengService;

@RestController
public class TichengController {

	@Autowired
	private TichengService tichengService;
	
	@GetMapping("/admin/tichengs/ticheng")
	public TichengEntity tichengGet(@RequestParam("form") int form) {
		return tichengService.getTicheng(form);
	}

	@PutMapping("/admin/tichengs/ticheng")
	public TichengEntity tichengUpdate(@RequestBody TichengEntity tichengEntity) {
		tichengService.updateTicheng(tichengEntity);
		return tichengEntity;
	}
	
	@GetMapping("admin/tichengs/liset/1")
	public PageResult tichengList( int pageSize, int page,String time){
		PageResult pageResult = new PageResult();
		pageResult.setData(tichengService.tichengList1( pageSize, page * pageSize,time));
		pageResult.setTotalCount(tichengService.tichengSize1(time));
		return pageResult;
	}
	
	@GetMapping("admin/tichengs/liset/2")
	public PageResult tichengList( int pageSize, int page){
		PageResult pageResult = new PageResult();
		pageResult.setData(tichengService.tichengList2( pageSize, page * pageSize));
		pageResult.setTotalCount(tichengService.tichengSize2());
		return pageResult;
	}
	
}
