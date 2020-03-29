package com.xms.dao;

import com.xms.annotation.MyAnnotation;
import com.xms.entity.User;

@MyAnnotation
public interface UserDao {
	// 根据
	User findUserByEmail(String email);

	// 注册
	void save(User user);
}
