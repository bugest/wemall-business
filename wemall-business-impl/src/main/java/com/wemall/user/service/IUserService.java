package com.wemall.user.service;

import com.wemall.user.entity.User;

public interface IUserService {
	User selectByPrimaryKey(Long id);

	User selectByAccount(String account);
}
