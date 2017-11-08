package com.seven.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seven.demo.dao.PersonDao;
import com.seven.demo.model.Page;
import com.seven.demo.model.Person;
import com.seven.demo.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService{
	
	@Autowired
	private PersonDao personDao;
	
	@Transactional
	@Override
	public void addPerson(String name, String gender) {
		// TODO Auto-generated method stub
		personDao.addPerson(name, gender);
	}
	
	@Transactional
	@Override
	public List<Person> findAll() {
		// TODO Auto-generated method stub
		List<Person> personList =personDao.fillAll();
		return personList;
	}
	
	@Transactional
	@Override
	public void deletePerson(Integer id) {
		// TODO Auto-generated method stub
		personDao.deletePerson(id);
	}
	
	@Transactional
	@Override
	public List<Person> findPage(Page page) {
		// TODO Auto-generated method stub
		return personDao.findPage(page);
	}
	
	@Transactional
	@Override
	public int getTotalCount() {
		// TODO Auto-generated method stub
		return personDao.getTotalCount();
	}

}
