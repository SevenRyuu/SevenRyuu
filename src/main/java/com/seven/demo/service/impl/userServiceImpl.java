package com.seven.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seven.demo.dao.UserDao;
import com.seven.demo.model.User;
import com.seven.demo.service.UserService;
import com.seven.demo.util.ResultResponse;

@Service
public class userServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;

	@Override
	@Transactional
	public ResultResponse addUser(User user) {
		// TODO Auto-generated method stub
		ResultResponse result = new ResultResponse();
		User oldUser = userDao.getByUsername(user.getUsername());
		
		if (oldUser != null) {
			result.setResult(false);;
			return result;
		}else{
			userDao.addUser(user);
			
		}
		
		return result;
	}

}
