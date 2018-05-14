package com.wh.weiguang;

import java.util.Random;

import org.junit.Test;

import com.wh.weiguang.util.MyStringUtil;

/*@RunWith(SpringRunner.class)
@SpringBootTest*/
public class WhWeiguangApplicationTests {

	@Test
	public void test002() {
		if(MyStringUtil.isInteger("1231w23123245")) {
			System.out.println("是数字");
		}else {
			System.out.println("不是数字");
		}
		
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
