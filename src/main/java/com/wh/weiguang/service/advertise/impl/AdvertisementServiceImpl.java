package com.wh.weiguang.service.advertise.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.spatial4j.core.io.GeohashUtils;
import com.wh.weiguang.dao.AdvertisementCommentDao;
import com.wh.weiguang.dao.AdvertisementDao;
import com.wh.weiguang.dao.UserDao;
import com.wh.weiguang.dao.UserDetailDao;
import com.wh.weiguang.exception.AdcertiseException;
import com.wh.weiguang.model.PageEntity;
import com.wh.weiguang.model.advertise.AdvCommentModel;
import com.wh.weiguang.model.advertise.AdvContentModel;
import com.wh.weiguang.model.advertise.AdvDetailModel;
import com.wh.weiguang.model.advertise.AdvInfoModel;
import com.wh.weiguang.model.advertise.AdvertisementContentEntity;
import com.wh.weiguang.model.advertise.AdvertisementDetailEntity;
import com.wh.weiguang.model.advertise.AdvertisementReceiveModel;
import com.wh.weiguang.model.me.AdvertisementCommentEntity;
import com.wh.weiguang.model.me.AdvertisementEntity;
import com.wh.weiguang.model.me.AdvertisementModel;
import com.wh.weiguang.model.me.MyAdvertisementEntity;
import com.wh.weiguang.model.sys.UserDetailEntity;
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
	private AdvertisementCommentDao advertisementCommentDao;

	@Autowired
	private MyProperties myProperties;

	@Autowired
	private UserService userService;

	@Autowired
	private UserDetailDao userDetailDao;

	@Autowired
	private UserDao userDao;

	@Override
	public List<MyAdvertisementEntity> getMyAdvertisementEntity(int userid, PageEntity pageEntity) {
		return advertisementDao.getMyAdvertisementEntity(userid, pageEntity);
	}

	@Override
	public String savePicture(MultipartFile picture) {

		String path = "/adv/" + DateUtil.currentTimes();

		String pictureUrl = null;
		try {
			if (picture != null) {
				String fileName = ImageUtil.saveImg(picture, myProperties.getPathsProperties().getImage() + path);
				pictureUrl = myProperties.getPathsProperties().getDomainName() + path + "/" + fileName;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return pictureUrl;
	}

	@Override
	@Transactional
	public void advertising(AdvertisementReceiveModel advertisementReceiveModel) {

		int userid = SecurityAuthenUtil.getId();

		/* 扣账户余额 */
		boolean flag = userService.consume(userid, advertisementReceiveModel.getMoney(), "发布广告红包金额");
		if (!flag) {
			throw new AdcertiseException("账户余额不足,请充值");
		}

		/* 分析请求参数得到advertisementEntity */
		AdvertisementEntity advertisementEntity = getAdvByModel(advertisementReceiveModel);
		advertisementDao.insertAdv(advertisementEntity);

		/* 分析请求参数+advid得到advertisementContentEntity */
		AdvertisementContentEntity advertisementContentEntity = getAdvContentByModel(advertisementEntity.getId(),
				advertisementReceiveModel);
		advertisementDao.insertAdvContent(advertisementContentEntity);

		/* 分析请求参数+advid得到advertisementDetailEntity */
		AdvertisementDetailEntity advertisementDetailEntity = getAdvDetailByModel(advertisementEntity.getId(),
				advertisementReceiveModel);
		advertisementDao.insertAdvDetail(advertisementDetailEntity);

		/* 判断用户类型如果为普通用户则改为发布用户 */
		UserDetailEntity userDetailEntity = userDetailDao.getUserDetailByUserid(userid);
		if (userDetailEntity.getCustomerType() == 0) {
			userDetailDao.changeCustomerType(userid, 2);
			userDao.changeCreateTime(userid, DateUtil.currentTimestamp());
		}

	}

	public AdvertisementEntity getAdvByModel(AdvertisementReceiveModel advertisementReceiveModel) {
		AdvertisementEntity adv = new AdvertisementEntity();
		adv.setUserid(SecurityAuthenUtil.getId());
		adv.setTime(DateUtil.currentTimestamp());
		adv.setTitle(advertisementReceiveModel.getTitle());
		if (advertisementReceiveModel.getImageurl() != null) {
			adv.setImageurl(advertisementReceiveModel.getImageurl());
		}

		Integer scope = advertisementReceiveModel.getScope();
		if (scope == 0) {
			adv.setScope(scope);
		} else if (scope == 1) {
			adv.setScope(scope);
			adv.setArea(advertisementReceiveModel.getArea());
		} else if (scope == 2) {
			int distance = advertisementReceiveModel.getDistance();
			if(distance < 2.4) {
				adv.setScope(5);
				adv.setDistance(distance);
				adv.setLon(advertisementReceiveModel.getLon());
				adv.setLat(advertisementReceiveModel.getLat());
				String geohash = GeohashUtils.encodeLatLon(adv.getLat(), adv.getLon(), 5);
				adv.setGeohash(geohash);
			}else if(distance < 19.5) {
				adv.setScope(4);
				adv.setDistance(distance);
				adv.setLon(advertisementReceiveModel.getLon());
				adv.setLat(advertisementReceiveModel.getLat());
				String geohash = GeohashUtils.encodeLatLon(adv.getLat(), adv.getLon(), 4);
				adv.setGeohash(geohash);
			}else {
				adv.setScope(3);
				adv.setDistance(distance);
				adv.setLon(advertisementReceiveModel.getLon());
				adv.setLat(advertisementReceiveModel.getLat());
				String geohash = GeohashUtils.encodeLatLon(adv.getLat(), adv.getLon(), 3);
				adv.setGeohash(geohash);
			}
			
		} /*else if (scope == 3) {
			adv.setScope(scope);
			adv.setLon(advertisementReceiveModel.getLon());
			adv.setLat(advertisementReceiveModel.getLat());
			String geohash = GeohashUtils.encodeLatLon(adv.getLat(), adv.getLon(), 4);
			adv.setGeohash(geohash);
		}*/

		return adv;
	}

	public AdvertisementContentEntity getAdvContentByModel(int advid,
			AdvertisementReceiveModel advertisementReceiveModel) {
		AdvertisementContentEntity advContent = new AdvertisementContentEntity();
		advContent.setAdvid(advid);
		advContent.setTitle(advertisementReceiveModel.getTitle());
		advContent.setTime(DateUtil.currentTimestamp());

		advContent.setForm(advertisementReceiveModel.getForm());
		Integer form = advertisementReceiveModel.getForm();
		if (form == 0) {
			advContent.setUrl(advertisementReceiveModel.getUrl());
		} else if (form == 1) {
			advContent.setContent(advertisementReceiveModel.getContent());
		} else if (form == 2) {
			advContent.setUrl(advertisementReceiveModel.getUrl());
		}

		return advContent;
	}

	public AdvertisementDetailEntity getAdvDetailByModel(int advid,
			AdvertisementReceiveModel advertisementReceiveModel) {

		AdvertisementDetailEntity advDetail = new AdvertisementDetailEntity();
		advDetail.setTotalMoney(advertisementReceiveModel.getMoney());
		advDetail.setMoney(advertisementReceiveModel.getMoney()*0.5);
		advDetail.setTotal(advertisementReceiveModel.getTotal());
		advDetail.setSurplus((int) (advertisementReceiveModel.getMoney() / advertisementReceiveModel.getTotal()));
		advDetail.setCorrectKeywords(advertisementReceiveModel.getCorrectKeywords());
		advDetail.setErrorKeywords(advertisementReceiveModel.getError_keywords());
		advDetail.setTime(DateUtil.currentTimestamp());
		advDetail.setAdvid(advid);

		return advDetail;
	}

	@Override
	public List<AdvertisementModel> getAllAdv(double lon, double lat, String area, PageEntity pageEntity) {

		String geohash = GeohashUtils.encodeLatLon(lat, lon);

		return advertisementDao.getAllAdv(area, geohash, pageEntity);
	}

	@Override
	@Transactional
	public AdvContentModel getAdvContent(int advid) {

		advertisementDao.addClickRate(advid);

		return advertisementDao.getAdvContent(advid);
	}

	@Override
	public void insertAdvComment(AdvertisementCommentEntity advertisementCommentEntity) {
		advertisementCommentEntity.setUserid(SecurityAuthenUtil.getId());
		advertisementCommentEntity.setTime(DateUtil.currentTimestamp());
		advertisementCommentDao.insertAdvComment(advertisementCommentEntity);
	}

	@Override
	public void addAdvCommentPop(int id) {
		advertisementCommentDao.addAdvCommentPop(id);
	}

	@Override
	public List<AdvCommentModel> getAllAdvComment(int advid, PageEntity pageEntity) {
		return advertisementCommentDao.getAllAdvComment(advid, pageEntity);
	}

	@Override
	public List<AdvDetailModel> getAdvDetail(int advid) {
		return advertisementDao.getAdvDetail(advid);
	}

	@Override
	public Integer getCount1() {
		return advertisementDao.getCount1();
	}

	@Override
	public Integer getCount2(String time) {
		return advertisementDao.getCount2(DateUtil.monthFirstday(time), DateUtil.monthLastday(time));
	}

	@Override
	public Integer getCount3(String time) {
		return advertisementDao.getCount3(DateUtil.daystart(time), DateUtil.dayend(time));
	}

	@Override
	public List<AdvInfoModel> advsList(int type, int pageSize, int start, String time) {
		if (type == 1) {
			return advertisementDao.getAdvs1(pageSize, start);
		} else if (type == 2) {
			return advertisementDao.getAdvs2(pageSize, start, DateUtil.monthFirstday(time),
					DateUtil.monthLastday(time));
		} else if (type == 3) {
			return advertisementDao.getAdvs3(pageSize, start, DateUtil.daystart(time), DateUtil.dayend(time));
		}
		return null;
	}

	@Override
	public Integer advsSize(int type, String time) {
		if (type == 1) {
			return advertisementDao.getAdvsSize1();
		} else if (type == 2) {
			return advertisementDao.getAdvsSize2(DateUtil.monthFirstday(time), DateUtil.monthLastday(time));
		} else if (type == 3) {
			return advertisementDao.getAdvsSize3(DateUtil.daystart(time), DateUtil.dayend(time));
		}
		return null;
	}

	@Override
	public List<AdvInfoModel> publishAdvsList(int pageSize, int start, String username, String mobile, String advid) {
		return advertisementDao.getPublishAdvs(pageSize,start,username,mobile,advid);
	}

	@Override
	public Integer publishAdvsSize(String username, String mobile, String advid) {
		return advertisementDao.getPublishAdvsSize(username,mobile,advid);
	}

	@Override
	public void changeAdvTop(AdvertisementEntity advertisementEntity) {
		if(advertisementEntity.getId() == null || advertisementEntity.getTop() == null) {
			return;
		}
		advertisementDao.changeAdvTop(advertisementEntity);
	}

	@Override
	public void deleteAdvs(List<String> groupId) {
		advertisementDao.deleteAdvs(groupId);
	}

}
