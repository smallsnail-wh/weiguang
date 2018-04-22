package com.wh.weiguang.service.me.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wh.weiguang.dao.ReadingRecordDao;
import com.wh.weiguang.model.me.AdvertisementModel;
import com.wh.weiguang.model.me.ReadingRecordEntity;
import com.wh.weiguang.service.me.ReadingRecordService;
import com.wh.weiguang.util.DateUtil;

@Service
public class ReadingRecordServiceImpl implements ReadingRecordService {

	@Autowired
	private ReadingRecordDao readingRecordDao;
	
	@Override
	public List<AdvertisementModel> getReadingRecordsByUserid(int userid) {
		
		return readingRecordDao.getReadingRecordsByUserid(userid);
	}

	@Override
	public void insertRecord(ReadingRecordEntity readingRecordEntity) {
		readingRecordEntity.setTime(DateUtil.currentTimestamp());
		readingRecordDao.insertRecord(readingRecordEntity);
	}

	@Override
	public void deleteReadingRecords(ReadingRecordEntity readingRecordEntity) {
		readingRecordDao.deleteReadingRecords(readingRecordEntity);
	}


}
