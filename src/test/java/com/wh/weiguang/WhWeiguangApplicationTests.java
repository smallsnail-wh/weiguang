package com.wh.weiguang;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.junit.Test;

/*@RunWith(SpringRunner.class)
@SpringBootTest*/
public class WhWeiguangApplicationTests {

	@Test
	public void test003() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		Calendar cale = Calendar.getInstance();
		cale.setTime(new Date(Long.valueOf("1526716066293")));
		cale.set(Calendar.HOUR_OF_DAY, 23);  
        cale.set(Calendar.MINUTE, 59);  
        cale.set(Calendar.SECOND, 59);
        System.out.println(String.valueOf(cale.getTimeInMillis()));
        System.out.println(format.format(cale.getTime()));
	}
	
	@Test
	public void test002() {
		Calendar cale = null;
        cale = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String firstday, lastday,daystart,dayend;
        // 获取前月的第一天
        cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        cale.set(Calendar.HOUR_OF_DAY, 0);  
        cale.set(Calendar.MINUTE, 0);  
        cale.set(Calendar.SECOND, 0);
        System.out.println(cale.getTime().getTime());
        firstday = format.format(cale.getTime());
        // 获取前月的最后一天
        cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 1);
        cale.set(Calendar.DAY_OF_MONTH, 0);
        cale.set(Calendar.HOUR_OF_DAY, 23);  
        cale.set(Calendar.MINUTE, 59);  
        cale.set(Calendar.SECOND, 59);
        System.out.println(cale.getTime().getTime());
        lastday = format.format(cale.getTime());
        
        System.out.println("本月第一天和最后一天分别是 ： " + firstday + " and " + lastday);
        
     // 获取前月的第一天
        cale = Calendar.getInstance();

        cale.set(Calendar.HOUR_OF_DAY, 0);  
        cale.set(Calendar.MINUTE, 0);  
        cale.set(Calendar.SECOND, 0);
        System.out.println(cale.getTime().getTime());
        daystart = format.format(cale.getTime());
        // 获取前月的最后一天
        cale = Calendar.getInstance();

        cale.set(Calendar.HOUR_OF_DAY, 23);  
        cale.set(Calendar.MINUTE, 59);  
        cale.set(Calendar.SECOND, 59);
        System.out.println(cale.getTime().getTime());
        dayend = format.format(cale.getTime());
        
        System.out.println(daystart + " and " + dayend);

	}
	
	@Test
	public void contextLoads() {
		LeftMoneyPackage _leftMoneyPackage = new LeftMoneyPackage();
		_leftMoneyPackage.setRemainMoney(100);
		_leftMoneyPackage.setRemainSize(10);
		

		while(_leftMoneyPackage.getRemainSize() > 0) {
			System.out.println(getRandomMoney(_leftMoneyPackage));
		}
		
		
	}
	
	public static double getRandomMoney(LeftMoneyPackage _leftMoneyPackage) {
	    // remainSize 剩余的红包数量
	    // remainMoney 剩余的钱
	    if (_leftMoneyPackage.remainSize == 1) {
	        _leftMoneyPackage.remainSize--;
	        return (double) Math.round(_leftMoneyPackage.remainMoney * 100) / 100;
	    }
	    Random r     = new Random();
	    double min   = 0.01; //
	    double max   = _leftMoneyPackage.remainMoney / _leftMoneyPackage.remainSize * 2;
	   /* double max = 10;*/
	    double money = r.nextDouble() * max;
	    money = money <= min ? 0.01: money;
	    money = Math.floor(money * 100) / 100;
	    _leftMoneyPackage.remainSize--;
	    _leftMoneyPackage.remainMoney -= money;
	    return money;
	}


	@Test
	public void test() {
		System.out.println(99.00*10/100);
	}
	
}
