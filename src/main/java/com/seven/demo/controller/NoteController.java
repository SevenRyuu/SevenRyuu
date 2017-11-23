package com.seven.demo.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public ModelAndView note(Model model){
		//System.out.println("helloworld");
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
			model.addAttribute("noteList", noteList);
			return new ModelAndView("noteList");
		
	}
}
