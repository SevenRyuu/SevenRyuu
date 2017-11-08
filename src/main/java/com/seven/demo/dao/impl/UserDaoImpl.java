package com.seven.demo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.seven.demo.dao.UserDao;
import com.seven.demo.model.User;

@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
    private SessionFactory sessionFactory;

    private Session getSession()
    {
        return sessionFactory.getCurrentSession();
    }

	@Override
	public User getByUsername(String username) {
		// TODO Auto-generated method stub
		List<User> user = new ArrayList<User>();
		Query query = getSession().createQuery("from User as u where u.username=?");
		query.setParameter(0,username);
		user = query.list();
		return user.isEmpty()? null : user.get(0);
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		getSession().save(user);
	}
    
    
	
}
