package com.wh.weiguang.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wh.weiguang.model.PageEntity;
import com.wh.weiguang.model.advertise.AdvContentModel;
import com.wh.weiguang.model.advertise.AdvDetailModel;
import com.wh.weiguang.model.advertise.AdvInfoModel;
import com.wh.weiguang.model.advertise.AdvertisementContentEntity;
import com.wh.weiguang.model.advertise.AdvertisementDetailEntity;
import com.wh.weiguang.model.me.AdvertisementEntity;
import com.wh.weiguang.model.me.AdvertisementModel;
import com.wh.weiguang.model.me.MyAdvertisementEntity;

@Mapper
public interface AdvertisementDao {

	public List<MyAdvertisementEntity> getMyAdvertisementEntity(@Param("userid") int userid,
			@Param("pageEntity") PageEntity pageEntity);

	public List<AdvertisementModel> getAdvModelByid(@Param("id") int id);

	public void insertAdv(AdvertisementEntity advertisementEntity);

	public void insertAdvContent(AdvertisementContentEntity advertisementContentEntity);

	public void insertAdvDetail(AdvertisementDetailEntity advertisementDetailEntity);

	public List<AdvertisementModel> getAllAdv(@Param("area") String area, @Param("geohash") String geohash,
			@Param("pageEntity") PageEntity pageEntity);

	public AdvContentModel getAdvContent(@Param("advid") int advid);

	public void addClickRate(@Param("id") int id);

	public List<AdvDetailModel> getAdvDetail(@Param("advid") int advid);

	public Integer getCount1();

	public Integer getCount2(@Param("timeStart") String monthFirstday, @Param("timeEnd") String monthLastday);

	public Integer getCount3(@Param("timeStart") String daystart, @Param("timeEnd") String dayend);

	public List<AdvInfoModel> getAdvs1(@Param("pageSize") int pageSize, @Param("start") int start);

	public Integer getAdvsSize1();

	public List<AdvInfoModel> getAdvs2(@Param("pageSize") int pageSize, @Param("start") int start,
			@Param("timeStart") String timeStart, @Param("timeEnd") String timeEnd);

	public Integer getAdvsSize2(@Param("timeStart") String timeStart, @Param("timeEnd") String timeEnd);

	public List<AdvInfoModel> getAdvs3(@Param("pageSize") int pageSize, @Param("start") int start,
			@Param("timeStart") String timeStart, @Param("timeEnd") String timeEnd);

	public Integer getAdvsSize3(@Param("timeStart") String timeStart, @Param("timeEnd") String timeEnd);

	public List<AdvInfoModel> getPublishAdvs(@Param("pageSize") int pageSize, @Param("start") int start,
			@Param("username") String username, @Param("mobile") String mobile, @Param("advid") String advid);

	public Integer getPublishAdvsSize(@Param("username") String username, @Param("mobile") String mobile,
			@Param("advid") String advid);

	public void changeAdvTop(AdvertisementEntity advertisementEntity);

	public void deleteAdvs(@Param("groupId") List<String> groupId);

}
