package com.wemall.user.service;

import com.wemall.user.entity.User;

public interface IUserService {
	public User selectByPrimaryKey(Long id);
}
