package com.seven.demo.dao;

import com.seven.demo.model.User;

public interface UserDao {
	User getByUsername(String username);
	
	void addUser(User user);
}
