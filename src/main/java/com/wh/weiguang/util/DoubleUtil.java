package com.wh.weiguang.util;

public class DoubleUtil {

	public static double wipeNull(Double number) {
		if(number == null) {
			return  (double) 0;
		}
		return number;
	}
	
}
