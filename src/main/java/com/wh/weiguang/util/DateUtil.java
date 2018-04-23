package com.wh.weiguang.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	/**
	 * 当前系统时间戳
	 * @return
	 */
	public static String currentTimestamp() {
		return String.valueOf(new Date().getTime());
	}
	
	/**
	 * 当前系统时间（yyyyMMdd）
	 * @return
	 */
	public static String currentTimes() {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		
		return format.format(new Date());
	}
	
}
