package com.wh.weiguang;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WhWeiguangApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void test() {
		Map<String,String> map = new HashMap<String,String>();
		
		map.put("id","001");
		map.put("name", "wanghuan");
		map.put("age", "25");
		
		for(Entry<String, String> entrySet:map.entrySet()) {
			System.out.println(entrySet.toString());
		}
		System.out.println("=============================");
		for(String key:map.keySet()) {
			System.out.println("key:"+key+",value:"+map.get(key));
		}
	}
	
}
