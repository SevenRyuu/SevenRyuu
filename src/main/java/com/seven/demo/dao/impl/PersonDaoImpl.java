package com.seven.demo.dao.impl;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.Projection;
import org.springframework.stereotype.Repository;

import com.seven.demo.dao.PersonDao;
import com.seven.demo.model.Page;
import com.seven.demo.model.Person;

@Repository
public class PersonDaoImpl implements PersonDao {
	
	@Autowired
    private SessionFactory sessionFactory;

    private Session getSession()
    {
        return sessionFactory.getCurrentSession();
    }
    

	@Override
	public void addPerson(String name, String gender) {
		// TODO Auto-generated method stub
		Person person = new Person();
		person.setName(name);
		person.setGender(gender);
		getSession().save(person);
	}


	@Override
	public List<Person> fillAll() {
		// TODO Auto-generated method stub
		List<Person> personAll = new ArrayList<Person>();
		Query query = getSession().createQuery("from Person");
		personAll = query.list();
		/*Criteria criteria=getSession().createCriteria(Person.class);*/
		//criteria.setProjection(Projections.rowCount());
		//criteria.setProjection(Projections.avg("name"));
		/*criteria.setProjection(Projections.projectionList().add(Projections.rowCount())
														   .add(Projections.avg("name"))
														   .add(Projections.max("name")));*/
		//criteria.setProjection(Projections.max("name"));
		/*personAll=criteria.list();*/
		return personAll;
	}


	@Override
	public void deletePerson(Integer id) {
		// TODO Auto-generated method stub
		
		String hql="delete Person as p where p.id=?";

		Query query=getSession().createQuery(hql);

		query.setInteger(0,id);

		query.executeUpdate();
	}
	
	@Override
	public List<Person> findPage(Page page) {
	    //1.获取session
	    Session session=getSession();
	    
	    //2.定义查询最大记录数的hql
	    String hql="from Person";
	    
	    //3.定义查询最大记录数的Query对象
	    Query querypage=session.createQuery(hql);
	    
	    //4.查询最大记录数的数据
	    querypage.setMaxResults(page.getPagesize());
	    
	    //5.确定查询起点
	    querypage.setFirstResult(page.getStartrow());
	    
	    //6.分页查询
	    List<Person> list=querypage.list();
	    
	    
	    return list;
	}
	
	@Override
	public int getTotalCount() {
	    //1.获取session
	    Session session=getSession();
	    
	    //2.定义查询总条数hql语句
	    String hqlcount="select count(*) from Person";
	    
	    //3.利用Session创建Query对象
	    Query querycount=session.createQuery(hqlcount);
	    
	    //4.获取总条数(返回单行数据uniqueResult())
	    Integer totalCount=Integer.parseInt(querycount.uniqueResult().toString());
	    return totalCount;
	}
	
	

}
