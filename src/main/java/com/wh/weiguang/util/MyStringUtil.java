package com.wh.weiguang.util;

import java.util.regex.Pattern;

public class MyStringUtil {

	public static boolean isInteger(String str) {  
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");  
        return pattern.matcher(str).matches();  
}
	
}
