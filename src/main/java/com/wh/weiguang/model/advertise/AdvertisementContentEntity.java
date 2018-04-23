package com.wh.weiguang.model.advertise;

/**
 * 新闻内容
 * @author wanghuan
 *
 */
public class AdvertisementContentEntity {
	
	private Integer id;
	
	private Integer devid;
	
	private Integer form;
	
	private String url;
	
	private String title;
	
	private String content;
	
	private Integer pop;
	
	private String time;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDevid() {
		return devid;
	}

	public void setDevid(Integer devid) {
		this.devid = devid;
	}

	public Integer getForm() {
		return form;
	}

	public void setForm(Integer form) {
		this.form = form;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getPop() {
		return pop;
	}

	public void setPop(Integer pop) {
		this.pop = pop;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
}
