package com.wemall.shop.service;

import java.util.List;

import com.wemall.shop.entity.Shop;

public interface ShopService {
	Shop selectByPrimaryKey(String pkShop);
	
	List<Shop> selectAllShop();
}
