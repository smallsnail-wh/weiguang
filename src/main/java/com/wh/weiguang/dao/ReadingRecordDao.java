package com.wh.weiguang.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wh.weiguang.model.me.AdvertisementModel;
import com.wh.weiguang.model.me.ReadingRecordEntity;

@Mapper
public interface ReadingRecordDao {

	public List<AdvertisementModel> getReadingRecordsByUserid(@Param("userid") int userid);

	public void insertRecord(ReadingRecordEntity readingRecordEntity);

	public void deleteReadingRecords(ReadingRecordEntity readingRecordEntity);

}
