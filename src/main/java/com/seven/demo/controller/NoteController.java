package com.seven.demo.controller;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.seven.demo.model.Note;
import com.seven.demo.service.NoteService;

@RestController
@RequestMapping("/seven/note")
public class NoteController {
	
	@Autowired
	private NoteService noteService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView note(Model model,HttpServletRequest request){
		//System.out.println("helloworld");
		/*UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
			    .getAuthentication()
			    .getPrincipal();*/
		SecurityContextImpl securityContextImpl = (SecurityContextImpl) request
				.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		model.addAttribute("username", securityContextImpl.getAuthentication().getName());
		return new ModelAndView("noteAdd");
	}
	
	@RequestMapping(value="/add", method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addNote(String username, String noteContent){
			noteService.addNote(username, noteContent);
			return new ModelAndView("redirect:/seven/note/"+username+"/noteList");
	}	
	
	@RequestMapping(value="/{username}/noteList", method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getNoteContent(@PathVariable("username")String username,Model model){
			Calendar cal = Calendar.getInstance(); 
			StringBuilder currentDate = new StringBuilder();
			currentDate.append(cal.get(Calendar.YEAR));
			currentDate.append("-");
			currentDate.append(cal.get(Calendar.MONTH )+1);
			currentDate.append("-");
			currentDate.append(cal.get(Calendar.DAY_OF_MONTH));
			List<Note> noteList = noteService.getByUsername(username,currentDate.toString());
			model.addAttribute("username", username);
			model.addAttribute("noteList", noteList);
			return new ModelAndView("noteList");
		
	}
}
