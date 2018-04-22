package com.wh.weiguang.controller.me;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wh.weiguang.model.me.AdvertisementModel;
import com.wh.weiguang.model.me.ReadingRecordEntity;
import com.wh.weiguang.service.me.ReadingRecordService;
import com.wh.weiguang.util.SecurityAuthenUtil;

@RestController
public class ReadingRecordController {

	@Autowired
	private ReadingRecordService readingRecordService;
	
	@GetMapping("/user/readrecord")
	public List<AdvertisementModel> readingRecordsGet() {
		return readingRecordService.getReadingRecordsByUserid(SecurityAuthenUtil.getId());
	}
	
	@PostMapping("/user/readrecord")
	public ReadingRecordEntity readingRecordsPost(@RequestBody ReadingRecordEntity readingRecordEntity) {
		readingRecordEntity.setUserid(SecurityAuthenUtil.getId());
		readingRecordService.insertRecord(readingRecordEntity);
		return readingRecordEntity;
	}
	
	@DeleteMapping("/user/readrecord")
	public ReadingRecordEntity readingRecordsDelete(@RequestBody ReadingRecordEntity readingRecordEntity) {
		readingRecordEntity.setUserid(SecurityAuthenUtil.getId());
		readingRecordService.deleteReadingRecords(readingRecordEntity);
		return readingRecordEntity;
	}
}
