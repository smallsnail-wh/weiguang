package com.wh.weiguang.service.advertise.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.spatial4j.core.io.GeohashUtils;
import com.wh.weiguang.dao.AdvertisementDao;
import com.wh.weiguang.exception.AdcertiseException;
import com.wh.weiguang.model.advertise.AdvertisementContentEntity;
import com.wh.weiguang.model.advertise.AdvertisementDetailEntity;
import com.wh.weiguang.model.advertise.AdvertisementReceiveModel;
import com.wh.weiguang.model.me.AdvertisementEntity;
import com.wh.weiguang.model.me.MyAdvertisementEntity;
import com.wh.weiguang.properties.MyProperties;
import com.wh.weiguang.service.advertise.AdvertisementService;
import com.wh.weiguang.service.sys.UserService;
import com.wh.weiguang.util.DateUtil;
import com.wh.weiguang.util.ImageUtil;
import com.wh.weiguang.util.SecurityAuthenUtil;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

	@Autowired
	private AdvertisementDao advertisementDao;
	
	@Autowired
	private MyProperties myProperties;
	
	@Autowired
	private UserService userService;
	
	@Override
	public List<MyAdvertisementEntity> getMyAdvertisementEntity(int userid) {
		return advertisementDao.getMyAdvertisementEntity(userid);
	}

	@Override
	public String savePicture(MultipartFile picture) {
		
		String path = "/adv/"+DateUtil.currentTimes();
		
		String pictureUrl = null;
		try {
			if(picture != null) {
				String fileName = ImageUtil.saveImg(picture, myProperties.getPathsProperties().getImage()+path);
				pictureUrl = myProperties.getPathsProperties().getDomainName()+path+"/"+fileName;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return pictureUrl;
	}

	@Override
	@Transactional
	public void advertising(AdvertisementReceiveModel advertisementReceiveModel) {
		
		boolean flag = userService.consume(SecurityAuthenUtil.getId(), advertisementReceiveModel.getMoney());
		if(!flag) {
			throw new AdcertiseException("账户余额不足,请充值");
		}
		AdvertisementEntity advertisementEntity = getAdvByModel(advertisementReceiveModel);
		advertisementDao.insertAdv(advertisementEntity);
		
		AdvertisementContentEntity advertisementContentEntity = getAdvContentByModel(advertisementEntity.getId(),advertisementReceiveModel);
		advertisementDao.insertAdvContent(advertisementContentEntity);
		
		AdvertisementDetailEntity advertisementDetailEntity = getAdvDetailByModel(advertisementEntity.getId(),advertisementReceiveModel);
		advertisementDao.insertAdvDetail(advertisementDetailEntity);
		
	}
	
	public AdvertisementEntity getAdvByModel(AdvertisementReceiveModel advertisementReceiveModel) {
		AdvertisementEntity adv = new AdvertisementEntity();
		adv.setUserid(SecurityAuthenUtil.getId());
		adv.setTime(DateUtil.currentTimestamp());
		adv.setTitle(advertisementReceiveModel.getTitle());
		if(advertisementReceiveModel.getImageurl() != null) {
			adv.setImageurl(advertisementReceiveModel.getImageurl());
		}
		
		Integer scope = advertisementReceiveModel.getScope();
		if(scope == 0) {
			adv.setScope(scope);
		}else if(scope == 1) {
			adv.setScope(scope);
			adv.setArea(advertisementReceiveModel.getArea());
		}else if(scope == 2) {
			adv.setScope(scope);
			adv.setLon(advertisementReceiveModel.getLon());
			adv.setLat(advertisementReceiveModel.getLat());
			String geohash = GeohashUtils.encodeLatLon(adv.getLat(), adv.getLon(), 5);
			adv.setGeohash(geohash);
		}else if(scope == 3) {
			adv.setScope(scope);
			adv.setLon(advertisementReceiveModel.getLon());
			adv.setLat(advertisementReceiveModel.getLat());
			String geohash = GeohashUtils.encodeLatLon(adv.getLat(), adv.getLon(), 4);
			adv.setGeohash(geohash);
		}
		
		return adv;
	}
	
	public AdvertisementContentEntity getAdvContentByModel(int advid,AdvertisementReceiveModel advertisementReceiveModel) {
		AdvertisementContentEntity advContent = new AdvertisementContentEntity();
		advContent.setAdvid(advid);
		advContent.setTitle(advertisementReceiveModel.getTitle());
		advContent.setTime(DateUtil.currentTimestamp());
		
		Integer form = advertisementReceiveModel.getForm();
		if(form == 0) {
			advContent.setUrl(advertisementReceiveModel.getUrl());
		}else if(form == 1) {
			advContent.setContent(advertisementReceiveModel.getContent());
		}
		
		return advContent;
	}
	
	public AdvertisementDetailEntity getAdvDetailByModel(int advid,AdvertisementReceiveModel advertisementReceiveModel) {
		
		AdvertisementDetailEntity advDetail = new AdvertisementDetailEntity();
		advDetail.setMoney(advertisementReceiveModel.getMoney());
		advDetail.setTotal(advertisementReceiveModel.getTotal());
		advDetail.setCorrectKeywords(advertisementReceiveModel.getCorrectKeywords());
		advDetail.setErrorKeywords(advertisementReceiveModel.getError_keywords());
		advDetail.setTime(DateUtil.currentTimestamp());
		advDetail.setAdvid(advid);
		
		return advDetail;
	}

}
