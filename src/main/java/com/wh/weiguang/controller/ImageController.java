package com.wh.weiguang.controller;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wh.weiguang.properties.MyProperties;
import com.wh.weiguang.util.ImageUtil;

@RestController
public class ImageController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	MyProperties myProperties;
	
	/*@PutMapping("/article/img/upload")
	public String uploadImg(@RequestParam("editormd-image-file") MultipartFile multipartFile) {
		if (multipartFile.isEmpty() || StringUtils.isBlank(multipartFile.getOriginalFilename())) {
			throw new RuntimeException("IMG_NOT_EMPTY");
		}
		String contentType = multipartFile.getContentType();
		if (!contentType.contains("")) {
			throw new RuntimeException("IMG_FORMAT_ERROR");
		}
		String root_fileName = multipartFile.getOriginalFilename();
		logger.info("上传图片:name={},type={}", root_fileName, contentType);
		String filePath = myProperties.getPathsProperties().getHeadPortrait();
		logger.info("图片保存路径={}", filePath);
		String file_name = null;
		try {
			file_name = ImageUtil.saveImg(multipartFile, filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return filePath+file_name;
	}*/
}
