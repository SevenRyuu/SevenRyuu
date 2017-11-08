package com.seven.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.seven.demo.model.Page;
import com.seven.demo.model.Person;
import com.seven.demo.service.PersonService;

@RestController
@RequestMapping("/seven/person")
public class PersonController {
	
	@Autowired
	private PersonService personService;

	@RequestMapping(value="/add", method={RequestMethod.GET,RequestMethod.POST})
	public List<Person> addPerson(String name, String gender){
			personService.addPerson(name, gender);
			List<Person> personList = personService.findAll();
			return personList;
	}
	
	@RequestMapping(value="/addTH", method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addPersonTH(Model model, String name, String gender){
			personService.addPerson(name, gender);
			List<Person> personList = personService.findAll();
			model.addAttribute("personList", personList);
			return new ModelAndView("personData");
	}
	
	@RequestMapping(value="/delete", method={RequestMethod.GET,RequestMethod.POST})
	public List<Person> deletePerson(Integer id){
		personService.deletePerson(id);
		List<Person> personList = personService.findAll();
		return personList;
	}
	
	@RequestMapping(value="/deleteTH", method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView deletePersonTH(Model model, Integer id){
		personService.deletePerson(id);
		List<Person> personList = personService.findAll();
		model.addAttribute("personList", personList);
		return new ModelAndView("personData");
	}
	
	@RequestMapping(value="/addPage", method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addPersonPage(Model model, String name, String gender,Integer pageno){
			personService.addPerson(name, gender);
			int pageSize = 7;
			int totalCount=personService.getTotalCount();
			Page page = new Page(pageSize,pageno,totalCount);
			List<Person> personList = personService.findPage(page);
			model.addAttribute("personList", personList);
			model.addAttribute("page", page);
			return new ModelAndView("personData");
	}
	
	@RequestMapping(value="/deletePage", method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView deletePersonPage(Model model, Integer id, Integer pageno){
		personService.deletePerson(id);
		int pageSize = 7;
		int totalCount=personService.getTotalCount();
		Page page = new Page(pageSize,pageno,totalCount);
		List<Person> personList = personService.findPage(page);
		if(personList.isEmpty()){
			page = new Page(pageSize , pageno -1,totalCount); 
			personList = personService.findPage(page);
		}
		model.addAttribute("personList", personList);
		model.addAttribute("page", page);
		return new ModelAndView("personData");
	}
	
	@RequestMapping(value="/personPage", method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView personPage(Model model,Integer pageno){
			int pageSize = 7;
			int totalCount=personService.getTotalCount();
			Page page = new Page(pageSize,pageno,totalCount);
			List<Person> personList = personService.findPage(page);
			model.addAttribute("personList", personList);
			model.addAttribute("page", page);
			return new ModelAndView("personData");
	}
	
	
}
