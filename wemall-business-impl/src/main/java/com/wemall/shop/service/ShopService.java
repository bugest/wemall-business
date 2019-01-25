package com.wemall.shop.service;

import java.util.List;

import com.wemall.shop.entity.Shop;

public interface ShopService {
	Shop selectByPrimaryKey(String pkShop);
	
	List<Shop> selectAllShop();
	
	int insert(Shop shop);
	
	int updateByPrimaryKeySelective(Shop shop);
	
	int updateByName(List<String> list);
	
	int deleteByPrimaryKey(String pkShop);
	
	Shop selecttestwithasoci(String pk_shop);
}
