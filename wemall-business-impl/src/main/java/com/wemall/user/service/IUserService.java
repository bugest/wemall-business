package com.wemall.user.service;

import java.util.List;
import java.util.Map;

import com.wemall.user.entity.User;

public interface IUserService {
	User selectByPrimaryKey(Long id);

	User selectByAccount(String account);
	
	int insert(User user); 
	
	int updateByPrimaryKey(User user);
	
	List<User> selectUserByJoin();
	
	List<User> selecttest(Map map);
}
