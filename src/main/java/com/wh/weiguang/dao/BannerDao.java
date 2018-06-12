package com.wh.weiguang.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wh.weiguang.model.advertise.BannerEntity;

@Mapper
public interface BannerDao {

	public List<BannerEntity> getAllBanners();

	public void insertBanner(BannerEntity bannerEntity);

	public void bannerUpdate(BannerEntity bannerEntity);

	public void deleteBanners(@Param("groupId")List<String> groupId);

}
