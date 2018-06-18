package com.wh.weiguang.alipay.comfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;

@Configuration
public class AlipayConfiguration {

	/**
	 * alipay-sdk-java
	 * 
	 * @return
	 */
	@Bean
	public AlipayClient alipayClient() {
		return new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key,
				"json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
	}

}
