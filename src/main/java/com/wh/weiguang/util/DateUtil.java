package com.wh.weiguang.util;

import java.util.Date;

public class DateUtil {

	/**
	 * 当前系统时间戳
	 * @return
	 */
	public static String currentTimestamp() {
		return String.valueOf(new Date().getTime());
	}
	
}
