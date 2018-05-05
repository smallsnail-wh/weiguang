package com.wh.weiguang.model.me;

import io.swagger.annotations.ApiModelProperty;

public class UserLevelEntity {

	@ApiModelProperty("id")
	private Integer id;
	
	@ApiModelProperty("会员等级")
	private String name;
	
	@ApiModelProperty("升级条件")
	private String condition;
	
	@ApiModelProperty("每日观看次数")
	private Integer vtimes;

	@ApiModelProperty("等级高低排序")
	private Integer sort;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public Integer getVtimes() {
		return vtimes;
	}

	public void setVtimes(Integer vtimes) {
		this.vtimes = vtimes;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
}
