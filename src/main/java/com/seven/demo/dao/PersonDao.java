package com.seven.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.seven.demo.model.Page;
import com.seven.demo.model.Person;



public interface PersonDao {
	
	 void addPerson(String name, String gender);
	 
	 List<Person> fillAll();
	 
	 void deletePerson(Integer id);
	 
	 List<Person> findPage(Page page);
	 
	 int getTotalCount();
}
