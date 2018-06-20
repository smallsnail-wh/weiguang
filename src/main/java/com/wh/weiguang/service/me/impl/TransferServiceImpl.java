package com.wh.weiguang.service.me.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wh.weiguang.dao.TransferDao;
import com.wh.weiguang.service.me.TransferService;

@Service
public class TransferServiceImpl implements TransferService {

	@Autowired
	private TransferDao transferDao;

}
