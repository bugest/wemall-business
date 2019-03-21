package com.wemall.user.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.aop.framework.AopContext;
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
		return 1;
		//return c;
		//throw new RuntimeException();
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int updateByPrimaryKey(User user) {
		User user2 = new User();
		user2.setId(new Long(2));
		//int a = ((IUserService)AopContext.currentProxy()).updateByPrimaryKey1(user2);
		int a = userDao.updateByPrimaryKey(user2);
		user2.setId(new Long(1));
		//如果事务自己内部调用自己加事务的方法，这时候事务是不生效的，只有代理的对象调用才生效，下边就是一种内部调用的例子，
		//如果requeie-new事务同时改一条数据，这时候两个事务就锁死了，是不可重入锁
		((IUserService)AopContext.currentProxy()).updateByPrimaryKey1(user2);
		//updateByPrimaryKey1(user2);
		return a;
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int updateByPrimaryKey1(User user) {
		return userDao.updateByPrimaryKey(user);
	}
	
	public List<User> selectUserByJoin() {
		return userDao.selectUserByJoin();
	}
	public List<User> selecttest(Map map) {
		// TODO Auto-generated method stub
		return userDao.selecttest(map);
	}
	@Transactional
	public int updateSexCount(User user) {
		//并发减库存
		int a = userDao.updateSexCount(user);
		if (a >0) {
			//如果上条成功，这里就增加一条数据
			shopService.insert(new Shop());	
		}
		return a;
		
	}
}
