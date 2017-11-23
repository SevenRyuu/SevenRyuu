package com.seven.demo.service;

import java.util.List;

import com.seven.demo.model.Note;

public interface NoteService {
		
		void addNote(String username,String noteContent);
		
		List<Note> findAll();
		
		List<Note> getByUsername(String username,String currentDate);
}
