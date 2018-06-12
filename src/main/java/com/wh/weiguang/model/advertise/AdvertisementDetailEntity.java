package com.wh.weiguang.model.advertise;

/**
 * 新闻明细表
 * @author wanghuan
 *
 */
public class AdvertisementDetailEntity {
	
	private Integer id;
	
	private Double money;
	
	private Double totalMoney;
	
	private Integer total;
	
	private Integer surplus;
	
	private String correctKeywords;
	
	private String errorKeywords;
	
	private String time;
	
	private Integer advid;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getSurplus() {
		return surplus;
	}

	public void setSurplus(Integer surplus) {
		this.surplus = surplus;
	}

	public String getCorrectKeywords() {
		return correctKeywords;
	}

	public void setCorrectKeywords(String correctKeywords) {
		this.correctKeywords = correctKeywords;
	}

	public String getErrorKeywords() {
		return errorKeywords;
	}

	public void setErrorKeywords(String errorKeywords) {
		this.errorKeywords = errorKeywords;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getAdvid() {
		return advid;
	}

	public void setAdvid(Integer advid) {
		this.advid = advid;
	}

	public Double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}
	
}
