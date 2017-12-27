package com.seven.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.seven.demo.model.User;
import com.seven.demo.service.UserService;
import com.seven.demo.util.ResultResponse;

@RestController
public class TestController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/index")
	public ModelAndView toIndex(){
		
		return new ModelAndView("index");
	}
	
	@RequestMapping("/login")
	public ModelAndView login(){
		
		return new ModelAndView("home");
	}
	
	@RequestMapping("/register")
	public ResultResponse register(User user){
			
		return userService.addUser(user);
	}
	
	@RequestMapping("/home")
	public ModelAndView hello(Model model){
		//System.out.println("helloworld");
		return new ModelAndView("home");
	}
}
