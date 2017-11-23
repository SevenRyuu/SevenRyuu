package com.seven.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seven.demo.dao.NoteDao;
import com.seven.demo.model.Note;
import com.seven.demo.service.NoteService;

@Service
public class NoteServiceImpl implements NoteService{
		
	@Autowired
	private NoteDao noteDao;
	
	@Transactional
	@Override
	public void addNote(String username, String noteContent) {
		// TODO Auto-generated method stub
		noteDao.addNote(username, noteContent);
	}
	
	@Transactional
	@Override
	public List<Note> findAll() {
		// TODO Auto-generated method stub
		List<Note> noteList =noteDao.fillAll();
		return noteList;
	}
	
	@Transactional
	@Override
	public List<Note> getByUsername(String username,String currentDate) {
		// TODO Auto-generated method stub
		List<Note> noteList = noteDao.getByUsername(username,currentDate);
		return noteList;
	}
}
