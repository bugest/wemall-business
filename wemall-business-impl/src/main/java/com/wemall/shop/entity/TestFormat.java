package com.wemall.shop.entity;

import java.util.Date;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.fastjson.annotation.JSONField;

@Configuration
public class TestFormat {
	private Date date;
	
	@Bean
	public Shop getShop() {
		Shop shop = new Shop();
		shop.setName("linan");
		return shop;
	}
	
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public Date getDate() {
		return new Date();
	}

	
	public void setDate(Date date) {
		this.date = date;
	}
}
