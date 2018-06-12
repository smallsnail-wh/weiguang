package com.wh.weiguang.controller.advertise;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wh.weiguang.model.PageEntity;
import com.wh.weiguang.model.ResponseEntity;
import com.wh.weiguang.model.advertise.AdvCommentModel;
import com.wh.weiguang.model.advertise.AdvContentModel;
import com.wh.weiguang.model.advertise.AdvDetailModel;
import com.wh.weiguang.model.advertise.AdvertisementReceiveModel;
import com.wh.weiguang.model.me.AdvertisementCommentEntity;
import com.wh.weiguang.model.me.AdvertisementEntity;
import com.wh.weiguang.model.me.AdvertisementModel;
import com.wh.weiguang.model.me.MyAdvertisementEntity;
import com.wh.weiguang.model.sys.PageResult;
import com.wh.weiguang.service.advertise.AdvertisementService;
import com.wh.weiguang.util.DateUtil;
import com.wh.weiguang.util.SecurityAuthenUtil;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
public class AdvertisementController {

	private ResponseEntity responseEntity = new ResponseEntity();

	@Autowired
	private AdvertisementService advertisementService;

	@ApiOperation("我的广告")
	@GetMapping("/adv/myadv")
	public List<MyAdvertisementEntity> getMyAdvertisementEntity(
			@ApiParam(value = "分页的参数，每页多少数据") @RequestParam("pageSize") int pageSize,
			@ApiParam(value = "分页的参数，第几页,从0开始") @RequestParam("page") int page) {
		PageEntity pageEntity = new PageEntity(pageSize, page);
		return advertisementService.getMyAdvertisementEntity(SecurityAuthenUtil.getId(),pageEntity);
	}

	@ApiOperation("编辑广告是上传图片，返回图片地址")
	@PostMapping("/adv/upload/picture")
	public Map<String, String> uploadPicture(@ApiParam("图片") @RequestParam("picture") MultipartFile picture) {

		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("time", DateUtil.currentTimestamp());

		String pictureUrl = advertisementService.savePicture(picture);
		resultMap.put("url", pictureUrl);
		return resultMap;
	}

	@ApiOperation("发布广告")
	@PostMapping("/adv/advertising")
	public ResponseEntity advertising(@RequestBody AdvertisementReceiveModel advertisementReceiveModel) {

		advertisementService.advertising(advertisementReceiveModel);

		responseEntity.setStatu(HttpStatus.SC_OK);
		responseEntity.setMessage("发布广告成功");
		return responseEntity;
	}

	@ApiOperation(value = "首页广告列表")
	@GetMapping("/adv/homepage")
	public List<AdvertisementModel> advAll(@ApiParam(value = "经度") @RequestParam("lon") double lon,
			@ApiParam(value = "纬度") @RequestParam("lat") double lat,
			@ApiParam(value = "用户定位区域") @RequestParam("area") String area,
			@ApiParam(value = "分页的参数，每页多少数据") @RequestParam("pageSize") int pageSize,
			@ApiParam(value = "分页的参数，第几页,从0开始") @RequestParam("page") int page) {

		PageEntity pageEntity = new PageEntity(pageSize, page);

		return advertisementService.getAllAdv(lon, lat, area, pageEntity);

	}

	@ApiOperation(value = "广告详情获取")
	@GetMapping("/adv/content")
	public AdvContentModel advContentGet(@ApiParam(value = "广告id") @RequestParam("advid") int advid) {

		return advertisementService.getAdvContent(advid);

	}

	@ApiOperation(value = "评论或者回复")
	@PostMapping("/adv/comment")
	public AdvertisementCommentEntity advComment(@RequestBody AdvertisementCommentEntity advertisementCommentEntity) {

		advertisementService.insertAdvComment(advertisementCommentEntity);

		return advertisementCommentEntity;
	}

	@ApiOperation(value = "评论点赞")
	@PutMapping("/adv/comment/like")
	public int advCommentLike(@ApiParam(value = "评论的id") @RequestParam("id") int id) {
		advertisementService.addAdvCommentPop(id);
		return id;
	}

	@ApiOperation(value = "广告评论列表")
	@GetMapping("/adv/comment")
	public List<AdvCommentModel> advCommentAll(@ApiParam(value = "广告id") @RequestParam("advid") int advid,
			@ApiParam(value = "分页的参数，每页多少数据") @RequestParam("pageSize") int pageSize,
			@ApiParam(value = "分页的参数，第几页,从0开始") @RequestParam("page") int page) {

		PageEntity pageEntity = new PageEntity(pageSize, page);

		return advertisementService.getAllAdvComment(advid, pageEntity);

	}

	@ApiOperation(value = "广告明细")
	@GetMapping("/adv/detail")
	public List<AdvDetailModel> advDetailGet(@ApiParam(value = "广告id") @RequestParam("advid") int advid) {

		return advertisementService.getAdvDetail(advid);
	}
	
	@GetMapping("/admin/publish/advs")
	public PageResult publishAdvsList(int pageSize, int page,String username,String mobile,String advid) {
		PageResult pageResult = new PageResult();
		pageResult.setData(advertisementService.publishAdvsList( pageSize, page * pageSize,username,mobile,advid));
		pageResult.setTotalCount(advertisementService.publishAdvsSize(username,mobile,advid));
		return pageResult;
	}
	
	@PutMapping("/admin/publish/advs/top")
	public AdvertisementEntity changeAdvTop(@RequestBody AdvertisementEntity advertisementEntity) {
		advertisementService.changeAdvTop(advertisementEntity);
		return advertisementEntity;
	}
	
	@DeleteMapping("/admin/publish/advs")
	public List<String> deleteAdvs(@RequestBody List<String> groupId) {
		advertisementService.deleteAdvs(groupId);
		return groupId;
	}
	
	@GetMapping("/admin/advs")
	public PageResult advsList(int type, int pageSize, int page,String time) {
		PageResult pageResult = new PageResult();
		pageResult.setData(advertisementService.advsList(type, pageSize, page * pageSize,time));
		pageResult.setTotalCount(advertisementService.advsSize(type,time));
		return pageResult;
	}
	
	/**
	 * 总发布数量
	 * 
	 * @return
	 */
	@GetMapping("/adv/count1")
	public Integer getCount1() {

		return advertisementService.getCount1();
	}

	/**
	 * 月发布数量
	 * 
	 * @return
	 */
	@GetMapping("/adv/count2")
	public Integer getCount2(@RequestParam("time") String time) {

		return advertisementService.getCount2(time);
	}

	/**
	 * 日发布数量
	 * 
	 * @return
	 */
	@GetMapping("/adv/count3")
	public Integer getCount3(@RequestParam("time") String time) {

		return advertisementService.getCount3(time);
	}

}
