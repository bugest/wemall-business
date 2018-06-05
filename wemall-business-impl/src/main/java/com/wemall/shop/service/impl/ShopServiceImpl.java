package com.wemall.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wemall.redis.service.impl.RedisTemplateUtil;
import com.wemall.shop.dao.ShopDao;
import com.wemall.shop.entity.Shop;
import com.wemall.shop.service.ShopService;

@Service
public class ShopServiceImpl implements ShopService {
	@Autowired
	private ShopDao shopDao;

	public Shop selectByPrimaryKey(String pkShop) {
		// TODO Auto-generated method stub
		return shopDao.selectByPrimaryKey(pkShop);
	}
	public List<Shop> selectAllShop() {
		// TODO Auto-generated method stub
		return shopDao.selectAllShop();
	}

}
