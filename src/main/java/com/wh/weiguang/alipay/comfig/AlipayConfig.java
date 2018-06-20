package com.wh.weiguang.alipay.comfig;



import java.io.FileWriter;

import java.io.IOException;



/* *

 *类名：AlipayConfig
 
 *功能：基础配置类

 *详细：设置帐户有关信息及返回路径

 *修改日期：2017-04-05

 *说明：

 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。

 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。

 */



public class AlipayConfig {

	

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号

	public static String app_id = "2018061460405345";

	

	// 商户私钥，您的PKCS8格式RSA2私钥

    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC92SfgioTCvV2UQ9nlDFJHC4EB7egDeVSmrNKOVSl/S6rj1ReX7qiJuBMujtFYOpaUtUZTpIv6WoGsSvWPHBUBDe1VAw9sVGXG2bNbFeW3AMq0lFVU3qz37A10DfvT0dXphi4eULUsmH0FRY+YpseYH3+au8Nod71dVpV8Oaj6Ce73vWk6uYTWhfMY4zltUEGn60QlDeuRmqZwsztKEO3eLgUAxoem/M2v8rPqW7B7KD4aIrr3SV/T9lp/ANSVJvWM7y6kL7ZaBemxKJrr+wSANjaVcVJV3a+sAQ7AgRWNgfN2gI5wZSrgVZUdMFm4eD17SPaYE05HR4/xAnqUQYpBAgMBAAECggEABeLlyaCB3UfhzucLqn5nTqyco2Ks/u6UIVbacG9Tig7ejZHd2Wgc8agzcA4dj47BfHriY1WGwYxs2Nq6ImXMgOvdn/UoVUOIpFG7redP6AyuPEsdGjSNSGKZSC/mzvqCl2sBow79jxRrS+6MNT92kNDpM/L+bA7KYEp++AsRoi7+/gJFTMp3gAL7BhemONGF7qSU6DD8xUwbFafPB+OqjObUZdfu1Lo38pazxCb79MQR09A13ZiJ0QSOH04IchCrs46WSWXMqlWRF6Ttl+lYU3r4Q1ug2PeGCIdE/7W6OxBcMGLib/D9oTvgejtzsas1Zvt7PjhclXRAZX9EBK6HwQKBgQDdp4gqo+B3q0OaCFsmLdFmBucOHbN79eANjVYeh5t7hYR4ZXHWDrpEG5iCneqYBtoWsmaMYEpS6oL+67sjO0xlvRJmtwjPx65ircB5GE/BLy+RSekJOg2gqZzSzyNiVfVc4Fq72uKlLXbx+pvB1tThJpNLu9XUU5z+d/N1Q/niDwKBgQDbQ/RS3W2tG9Qbcvdn2bt83i4YMvUF/2XjP7k/ELuww1K1V0H2jQqD7zsn3jysI6wQPSXMGDSKszsGbXUYJEu5nCwrtTQ/P2o+zDkcLYDm/fcOUxrnxhjAY3y8EkhKTGNcCSl/lRP2TOJ0ANNXvX4vtVqtq2h2MgyaUs1RainerwKBgAEnPwEhyyg0PuzNQFQpht5mCMu9AdMSS3EmnB0ajYeOAUtzE62OdA+oqRl84tyZpIbbtMbkNV7LE5Vp+pZLV7Fr022vZB9YpaKjzs2Sf8+8hB3YaZr/r1R3rXY3V7LKSbWLAU3a7LT5UJYfvLG8gW0Oe1O4jXtGWBnG3ABvMxynAoGAHjOO/Lb5O2Xg4mMEhu5rr7heuwCR1WCnZRTK7E/eums9AjNhp5cnwqJ6EHTBAKjtM1No2GLhIdWsJKeBAHFHaOqE/wQ3jRqsBLhWiXYPEkIa/IMf6prTn/RUIweVo35AFVZBvAwJstxGONpAQg6cye997Pv2KImeSMds9nDcEYsCgYEAv+YwkXGJULgrQuCwkMm/kX4j++4mi1DrMtXzznBdENMPX8nte02OrOF20IeaL1IrLEkPLGVM/vldsNXmK8aB55ct97yGNDyeZ16+sm5j/k0gCTLvDDrN20F9bhmscCfVNRfR+c7Fjoqcs2YD7RpUpcZIvHs4Xl1ZizTphp8ODmE=";

	

	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。

    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvdkn4IqEwr1dlEPZ5QxSRwuBAe3oA3lUpqzSjlUpf0uq49UXl+6oibgTLo7RWDqWlLVGU6SL+lqBrEr1jxwVAQ3tVQMPbFRlxtmzWxXltwDKtJRVVN6s9+wNdA3709HV6YYuHlC1LJh9BUWPmKbHmB9/mrvDaHe9XVaVfDmo+gnu971pOrmE1oXzGOM5bVBBp+tEJQ3rkZqmcLM7ShDt3i4FAMaHpvzNr/Kz6luweyg+GiK690lf0/ZafwDUlSb1jO8upC+2WgXpsSia6/sEgDY2lXFSVd2vrAEOwIEVjYHzdoCOcGUq4FWVHTBZuHg9e0j2mBNOR0eP8QJ6lEGKQQIDAQAB";



	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问

	public static String notify_url = "http://39.106.190.253:8080:8080/weiguang/public/alipay/notify";



	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问

	public static String return_url = "http://39.106.190.253:8080:8080/weiguang/public/alipay/returnUrl";



	// 签名方式

	public static String sign_type = "RSA2";

	

	// 字符编码格式

	public static String charset = "utf-8";

	

	// 支付宝网关

	public static String gatewayUrl = "https://openapi.alipay.com/gateway.do";

	

	// 支付宝网关

	public static String log_path = "D:/myfile/log/";







    /** 

     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）

     * @param sWord 要写入日志里的文本内容

     */

    public static void logResult(String sWord) {

        FileWriter writer = null;

        try {

            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");

            writer.write(sWord);

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            if (writer != null) {

                try {

                    writer.close();

                } catch (IOException e) {

                    e.printStackTrace();

                }

            }

        }

    }

}



