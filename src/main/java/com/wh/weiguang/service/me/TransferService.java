package com.wh.weiguang.service.me;

import java.util.List;

import com.wh.weiguang.model.me.TransferRecordEntity;
import com.wh.weiguang.model.me.TransferRecordModel;

public interface TransferService {

	public void applyTiXian(int id, Double amount);

	public List<TransferRecordModel> transferList(int pageSize, int start);

	public Integer transferSize();

	public TransferRecordEntity getTransferRecordById(String id);


}
