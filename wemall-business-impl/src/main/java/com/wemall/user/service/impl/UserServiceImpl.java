package com.wemall.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wemall.user.dao.UserDao;
import com.wemall.user.entity.User;
import com.wemall.user.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserDao userDao;
	@Transactional
	public User selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return userDao.selectByPrimaryKey(id);
	}
	public User selectByAccount(String account) {
		return userDao.selectByAccount(account);
	}
	@Transactional
	public int insert(User user) {
		// TODO Auto-generated method stub
		return userDao.insert(user);
	}
	public int updateByPrimaryKey(User user) {
		return userDao.updateByPrimaryKey(user);
	}
	
	

}
