package com.wh.weiguang.util;

import java.util.Random;

public class RedPacketUtil {

	/**
	 * 抢红包算法
	 * @param remainSize
	 * @param remainMoney
	 * @return
	 */
	public static double getRandomMoney(int remainSize, double remainMoney) {
	    // remainSize 剩余的红包数量
	    // remainMoney 剩余的钱
	    if (remainSize == 1) {
	        /*remainSize--;*/
	        return (double) Math.round(remainMoney * 100) / 100;
	    }
	    Random r     = new Random();
	    double min   = 0.01; //
	    double max   = remainMoney / remainSize * 2;
	   /* double max = 10;*/
	    double money = r.nextDouble() * max;
	    money = money <= min ? 0.01: money;
	    money = Math.floor(money * 100) / 100;
	    /*remainSize--;
	    remainMoney -= money;*/
	    return money;
	}
	
	/**
	 * 概率（0：抽中，1：为抽中）
	 * @param proportion
	 * @return
	 */
	public static int getProbability(int proportion) {
		Random r = new Random();
        int n = r.nextInt(100);
        if (n < proportion) {
            return 0;
        }
        return 1;
	}
	
}
