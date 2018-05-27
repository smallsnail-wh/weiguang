package com.wh.weiguang.login.validate.sms;

/**
 * 华信科技短信接口参数实例
 * @author tingting
 *
 */
public class HuanXinSmsEntity {

	/**
	 * 短息发送地址
	 */
	public final static String sendUrl = "https://dx.ipyy.net/smsJson.aspx";
	
	/**
	 * 企业id
	 */
	public final static String userid = "weiguang";
	
	/**
	 * 发送用户帐号
	 */
	public final static String account = "AA00126";
	
	/**
	 * 发送帐号密码
	 */
//	public final static String password = "A1F87D793644E36AEDE64C0D78A1BC5D";
	public final static String password = "A2A8A8C3729076315CCDFD8E5F364E27";
	
	/**
	 * 定时发送时间
	 */
	public final static String sendTime = "";
	
	/**
	 * 发送任务命令
	 */
	public final static String action = "send";
	
	/**
	 * 扩展子号
	 */
	public final static String extno = "";
	
	/**
	 * 全部被叫号码
	 */
	private String mobile;
	
	/**
	 * 发送内容
	 */
	private String content;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
