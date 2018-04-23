package com.wh.weiguang.model;
/**
 * 自定义返回结果
 * @author tingting
 *
 */

public class ResponseEntity {

	private Integer statu;
	
	private String message;
	
	public Integer getStatu() {
		return statu;
	}
	public void setStatu(Integer statu) {
		this.statu = statu;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
