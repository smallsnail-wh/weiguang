package com.wh.weiguang.controller.me;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wh.weiguang.model.PageEntity;
import com.wh.weiguang.model.me.AdvertisementModel;
import com.wh.weiguang.model.me.ReadingRecordEntity;
import com.wh.weiguang.service.me.ReadingRecordService;
import com.wh.weiguang.util.SecurityAuthenUtil;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
public class ReadingRecordController {

	@Autowired
	private ReadingRecordService readingRecordService;

	@ApiOperation("我的浏览记录")
	@GetMapping("/user/readrecord")
	public List<AdvertisementModel> readingRecordsGet(
			@ApiParam(value = "分页的参数，每页多少数据") @RequestParam("pageSize") int pageSize,
			@ApiParam(value = "分页的参数，第几页,从0开始") @RequestParam("page") int page) {
		PageEntity pageEntity = new PageEntity(pageSize, page);
		return readingRecordService.getReadingRecordsByUserid(SecurityAuthenUtil.getId(),pageEntity);
	}

	@ApiOperation("增添浏览记录")
	@PostMapping("/user/readrecord")
	public ReadingRecordEntity readingRecordsPost(@ApiParam(value = "阅读的广告id") @RequestParam("advid") int advid) {
		ReadingRecordEntity readingRecordEntity = new ReadingRecordEntity();
		readingRecordEntity.setAdvid(advid);
		readingRecordEntity.setUserid(SecurityAuthenUtil.getId());
		readingRecordService.insertRecord(readingRecordEntity);
		return readingRecordEntity;
	}

	@ApiOperation("删除浏览记录")
	@DeleteMapping("/user/readrecord")
	public ReadingRecordEntity readingRecordsDelete(@ApiParam(value = "阅读的广告id") @RequestParam("advid") int advid) {
		ReadingRecordEntity readingRecordEntity = new ReadingRecordEntity();
		readingRecordEntity.setAdvid(advid);
		readingRecordEntity.setUserid(SecurityAuthenUtil.getId());
		readingRecordService.deleteReadingRecords(readingRecordEntity);
		return readingRecordEntity;
	}
}
