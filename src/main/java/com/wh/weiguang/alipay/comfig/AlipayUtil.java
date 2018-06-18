package com.wh.weiguang.alipay.comfig;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;

public class AlipayUtil {

	/**
	 * 校验签名
	 * 
	 * @param request
	 * @return
	 */
	public static boolean rsaCheckV1(HttpServletRequest request) {
		// https://docs.open.alipay.com/54/106370
		// 获取支付宝POST过来反馈信息
		Map<String, String> params = new HashMap<>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			params.put(name, valueStr);
		}

		try {
			boolean verifyResult = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key,
					AlipayConfig.charset, AlipayConfig.sign_type);

			return verifyResult;
		} catch (AlipayApiException e) {
			return false;
		}
	}

	/**
	 * 得到商品订单号
	 * 
	 * @return
	 */
	public static String getOutTradeNo() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
		Date date = new Date();
		String key = format.format(date);

		Random r = new Random();
		String ranNumber = "";
		for (int i = 0; i < 5; i++) {
			ranNumber = ranNumber + String.valueOf(r.nextInt(10));
		}
		
		key = key + ranNumber;
		return key;
	}
}
