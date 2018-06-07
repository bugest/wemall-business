package com.alibaba.dubbo.demo.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.demo.DemoService;
import com.wemall.user.dao.UserDao;


public class DemoServiceImpl implements DemoService ,Serializable{

	
	@Autowired
	private UserDao userDao;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<String> getPermissions(Long id) {
		userDao.selectByAccount("");
        return null;
    }
}
