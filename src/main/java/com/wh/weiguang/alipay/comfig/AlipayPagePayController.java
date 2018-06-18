package com.wh.weiguang.alipay.comfig;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.wh.weiguang.model.me.RechargeRecordEntity;
import com.wh.weiguang.service.me.RechargService;
import com.wh.weiguang.util.DateUtil;
import com.wh.weiguang.util.SecurityAuthenUtil;

@RestController
@RequestMapping("/public")
public class AlipayPagePayController {

	@Autowired
	private RechargService rechargService;
	
	@Autowired
	private AlipayClient alipayClient;
	
	@PostMapping("/alipay/pc")
	public Map<String, String> PcAlipy(@RequestParam("amount") Double amount) {
		
		/*交易订单生成*/
		RechargeRecordEntity rechargeRecordEntity = new RechargeRecordEntity();
		rechargeRecordEntity.setId(AlipayUtil.getOutTradeNo());
		rechargeRecordEntity.setAmount(amount);
		rechargeRecordEntity.setForm(0);
		rechargeRecordEntity.setSucc(0);
		rechargeRecordEntity.setTime(DateUtil.currentTimestamp());
		rechargeRecordEntity.setUserid(SecurityAuthenUtil.getId());
		//rechargeRecordEntity.setUserid(8888);
		
		//获得初始化的AlipayClient
//        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
        
        /*结果返回url*/
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        
        alipayRequest.setBizContent("{" +
                "    \"out_trade_no\":\""+rechargeRecordEntity.getId()+"\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":"+rechargeRecordEntity.getAmount()+"," +
                "    \"subject\":\"微广充值\"," +
                "    \"body\":\"微广用户充值\"," +
//                "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"," +
                "    \"extend_params\":{" +
                "    \"sys_service_provider_id\":\"2088131534177640\"" +
                "    }"+
                "  }");//填充业务参数
 
        String payUrl = "";
        try {
        	//这里使用GET的方式，这样就能生成支付链接
            payUrl = alipayClient.pageExecute(alipayRequest, "GET").getBody(); //调用SDK生成表单
 
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        
        //订单生成
        rechargService.orderCreate(rechargeRecordEntity);
        
        Map<String,String> map=new HashMap<>();
        map.put("state", "200");
        map.put("payUrl",payUrl);
        return map;
	}
	
	@PostMapping("/alipay/app")
	public String AppAlipay(@RequestParam("amount") Double amount) {
		
		/*交易订单生成*/
		RechargeRecordEntity rechargeRecordEntity = new RechargeRecordEntity();
		rechargeRecordEntity.setId(AlipayUtil.getOutTradeNo());
		rechargeRecordEntity.setAmount(amount);
		rechargeRecordEntity.setForm(0);
		rechargeRecordEntity.setSucc(0);
		rechargeRecordEntity.setTime(DateUtil.currentTimestamp());
		rechargeRecordEntity.setUserid(SecurityAuthenUtil.getId());
		//rechargeRecordEntity.setUserid(8888);
		
		//实例化客户端
		//AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, "RSA2");
		//实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
		AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
		//SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
		AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
		model.setBody("微广用户充值");
		model.setSubject("微广充值");
		model.setOutTradeNo(rechargeRecordEntity.getId());
		model.setTimeoutExpress("30m");
		model.setTotalAmount(String.valueOf(rechargeRecordEntity.getAmount()));
		model.setProductCode("QUICK_MSECURITY_PAY");
		request.setBizModel(model);
		request.setNotifyUrl(AlipayConfig.notify_url);
		try {
		        //这里和普通的接口调用不同，使用的是sdkExecute
		        AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
		        String orderString = response.getBody();
		        
		        rechargService.orderCreate(rechargeRecordEntity);
		        
		        return orderString;//就是orderString 可以直接给客户端请求，无需再做处理。
		        
		    } catch (AlipayApiException e) {
		        e.printStackTrace();
		}
		return "FAIL";
	}
	
}
