package com.seven.demo.dao;

import java.util.List;

import com.seven.demo.model.Note;

public interface NoteDao {
	
	void addNote(String username,String noteContent);
	
	List<Note> fillAll();
	
	List<Note> getByUsername(String username,String currentDate);
}
