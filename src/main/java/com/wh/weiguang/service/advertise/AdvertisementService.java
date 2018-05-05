package com.wh.weiguang.service.advertise;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.wh.weiguang.model.PageEntity;
import com.wh.weiguang.model.advertise.AdvCommentModel;
import com.wh.weiguang.model.advertise.AdvContentModel;
import com.wh.weiguang.model.advertise.AdvDetailModel;
import com.wh.weiguang.model.advertise.AdvertisementReceiveModel;
import com.wh.weiguang.model.me.AdvertisementCommentEntity;
import com.wh.weiguang.model.me.AdvertisementModel;
import com.wh.weiguang.model.me.MyAdvertisementEntity;

public interface AdvertisementService {
	
	public List<MyAdvertisementEntity> getMyAdvertisementEntity(int userid, PageEntity pageEntity);

	public String savePicture(MultipartFile picture);

	/**
	 * 发布广告
	 * @param advertisementReceiveModel
	 */
	public void advertising(AdvertisementReceiveModel advertisementReceiveModel);

	/**
	 * 首页广告列表获取
	 * @param lon
	 * @param lat
	 * @param area
	 * @param pageSize
	 * @param page
	 * @return
	 */

	public List<AdvertisementModel> getAllAdv(double lon, double lat, String area, PageEntity pageEntity);

	/**
	 * 广告详情获取
	 * @param advid
	 * @return
	 */
	public AdvContentModel getAdvContent(int advid);

	/**
	 * 插入评论或者回复
	 * @param advertisementCommentEntity
	 */
	public void insertAdvComment(AdvertisementCommentEntity advertisementCommentEntity);

	/**
	 * 增加评论的点赞量
	 * @param id
	 */
	public void addAdvCommentPop(int id);

	/**
	 * 得到广告评论列表
	 * @param advid
	 * @param pageEntity
	 * @return
	 */
	public List<AdvCommentModel> getAllAdvComment(int advid, PageEntity pageEntity);

	/**
	 * 得到广告明细
	 * @param advid
	 * @return
	 */
	public List<AdvDetailModel> getAdvDetail(int advid);
	
}
