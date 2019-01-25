package com.wemall.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wemall.shop.dao.ShopDao;
import com.wemall.shop.entity.Shop;
import com.wemall.shop.service.ShopService;

@Service
@CacheConfig(cacheNames = "categories")
public class ShopServiceImpl implements ShopService {
	@Autowired
	private ShopDao shopDao;

	@Cacheable(key = "'CategoryModelList'")
	public Shop selectByPrimaryKey(String pkShop) {
		// TODO Auto-generated method stub
		return shopDao.selectByPrimaryKey(pkShop);
	}
	@Transactional(isolation=Isolation.READ_UNCOMMITTED)
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
	public int updateByName(List<String> list) {
		int a = shopDao.updateByName(list);
		return a;
	}
	public int deleteByPrimaryKey(String pkShop) {
		// TODO Auto-generated method stub
		int result = shopDao.deleteByPrimaryKey(pkShop);
		return result;
	}
	
	public Shop selecttestwithasoci(String pk_shop) {
		Shop selecttestwithasoci = shopDao.selecttestwithasoci(pk_shop);
		return selecttestwithasoci;
	}
}
