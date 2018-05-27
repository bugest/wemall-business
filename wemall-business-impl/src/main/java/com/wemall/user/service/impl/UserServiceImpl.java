package com.wemall.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wemall.user.dao.UserDao;
import com.wemall.user.entity.User;
import com.wemall.user.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserDao userDao;
	@Override
	public User selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return userDao.selectByPrimaryKey(id);
	}
	@Override
	public User selectByAccount(String account) {
		return userDao.selectByAccount(account);
	}

}
