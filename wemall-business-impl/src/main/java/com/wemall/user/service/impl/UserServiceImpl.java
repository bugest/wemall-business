package com.wemall.user.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wemall.shop.entity.Shop;
import com.wemall.shop.service.ShopService;
import com.wemall.user.dao.UserDao;
import com.wemall.user.entity.User;
import com.wemall.user.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ShopService shopService;
	@Transactional
	public User selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return userDao.selectByPrimaryKey(id);
	}
	public User selectByAccount(String account) {
		return userDao.selectByAccount(account);
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int insert(User user) {
		// TODO Auto-generated method stub
		
		User usera = new User();
		usera.setId(new Long(212));
		//usera.setId(new Long(151));
		Shop shop = new Shop();
		shop.setPkShop("1");
		shop.setLasteditTime(new Date());
		int c = shopService.updateByPrimaryKeySelective(shop);
		usera.setUserBirthday(new Date().toLocaleString());
		userDao.updateByPrimaryKeySelective(usera);		
		//return 1;
		//return c;
		//throw new RuntimeException();
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int updateByPrimaryKey(User user) {
		return userDao.updateByPrimaryKey(user);
		//throw new RuntimeException();
	}
	
	public List<User> selectUserByJoin() {
		return userDao.selectUserByJoin();
	}
}
