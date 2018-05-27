package com.wh.weiguang.login.validate.sms;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class DefaultSmscodeSender implements SmsCodeSender {

	private RestTemplate template = new RestTemplate();
	
	@Override
	public void send(String mobile, String code) {

		/*换成正在的发送短信接口*/
		/*System.out.println("向手机：" + mobile + "发送短信验证码" + code);getClass();
		log.info("***********************************");
		log.info("向手机：" + mobile + "发送短信验证码" + code);
		log.info("***********************************");*/
		
		String url = HuanXinSmsEntity.sendUrl;
		
		MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.add("userid", HuanXinSmsEntity.userid);
        requestEntity.add("account", HuanXinSmsEntity.account);
        requestEntity.add("password", HuanXinSmsEntity.password);
        requestEntity.add("sendTime", HuanXinSmsEntity.sendTime);
        requestEntity.add("action", HuanXinSmsEntity.action);
        requestEntity.add("extno", HuanXinSmsEntity.extno);
        
        requestEntity.add("mobile", mobile);
        requestEntity.add("content", getContent(code));
        
		ResponseEntity<String> responseEntity = template.postForEntity(url,requestEntity ,String.class);
		try {
			JSONObject returnJson = new JSONObject(responseEntity.getBody().trim());
			String returnstatus = returnJson.getString("returnstatus");
			if(!"Success".equals(returnstatus)) {
				throw new SmsCodeException("短息发送失败");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (SmsCodeException e) {
			e.printStackTrace();
		}
	}
	
	public String getContent(String code) {
		String content = "【微广】验证码："+code+"，本验证码有效时间1分钟，请勿告知他人";
		return content;
	}

}
