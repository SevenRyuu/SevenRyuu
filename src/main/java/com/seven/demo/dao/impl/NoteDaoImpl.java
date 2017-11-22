package com.seven.demo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.seven.demo.dao.NoteDao;
import com.seven.demo.model.Note;

@Repository
public class NoteDaoImpl implements NoteDao{
	
	@Autowired
    private SessionFactory sessionFactory;

    private Session getSession()
    {
        return sessionFactory.getCurrentSession();
    }
    
    @Override
	public void addNote(String username, String noteContent) {
		// TODO Auto-generated method stub
		Note note = new Note();
		note.setUsername(username);
		note.setNotecontent(noteContent);
		getSession().save(note);
	}
    
    @Override
	public List<Note> fillAll() {
		// TODO Auto-generated method stub
		List<Note> noteAll = new ArrayList<Note>();
		Query query = getSession().createQuery("from Note");
		noteAll = query.list();
		/*Criteria criteria=getSession().createCriteria(Person.class);*/
		//criteria.setProjection(Projections.rowCount());
		//criteria.setProjection(Projections.avg("name"));
		/*criteria.setProjection(Projections.projectionList().add(Projections.rowCount())
														   .add(Projections.avg("name"))
														   .add(Projections.max("name")));*/
		//criteria.setProjection(Projections.max("name"));
		/*personAll=criteria.list();*/
		return noteAll;
	}
    
    @Override
	public List<String> getByUsername(String username) {
		// TODO Auto-generated method stub
		List<String> noteContent = new ArrayList<String>();
		Query query = getSession().createQuery("select notecontent from Note as n where n.username=?");
		query.setParameter(0,username);
		noteContent = query.list();
		return noteContent;
	}
}
