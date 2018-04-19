package com.wemall.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wemall.shop.entity.Shop;
import com.wemall.shop.service.ShopService;

@Controller
@RequestMapping("/shop")
public class ShopController {
	@Autowired
	private ShopService shopService;

	@RequestMapping("/selectById")
	@ResponseBody
	public Shop selectShopById(String pkShop) {
		return shopService.selectByPrimaryKey(pkShop);
	}
	
	@RequestMapping("/selectAllShop")
	@ResponseBody
	public List<Shop> selectAllShop() {
		return shopService.selectAllShop();
	}
}
