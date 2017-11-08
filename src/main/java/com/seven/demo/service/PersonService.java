package com.seven.demo.service;

import java.util.List;

import com.seven.demo.model.Page;
import com.seven.demo.model.Person;

public interface PersonService {

		void addPerson(String name, String gender);
		
		List<Person> findAll();
		
		void deletePerson(Integer id);
		
		List<Person> findPage(Page page);
		 
		int getTotalCount();
}
