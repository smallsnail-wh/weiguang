package com.wh.weiguang.service.me;

import java.util.List;

import com.wh.weiguang.model.me.AdvertisementModel;
import com.wh.weiguang.model.me.ReadingRecordEntity;

public interface ReadingRecordService {

	public List<AdvertisementModel> getReadingRecordsByUserid(int userid);

	public void insertRecord(ReadingRecordEntity readingRecordEntity);

	public void deleteReadingRecords(ReadingRecordEntity readingRecordEntity);
	
}
