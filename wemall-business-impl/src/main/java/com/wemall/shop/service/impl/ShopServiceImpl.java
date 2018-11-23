package com.wemall.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int insert(Shop shop) {
		return shopDao.insert(shop);
	}
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public int updateByPrimaryKeySelective(Shop shop) {
		int a = shopDao.updateByPrimaryKeySelective(shop);
		return a;
	}
}
