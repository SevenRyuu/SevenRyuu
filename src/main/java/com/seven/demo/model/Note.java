package com.seven.demo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="Note")
@DynamicInsert
@DynamicUpdate
public class Note implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8885736800516645435L;
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id ;

	@Column(name="username")
	private String username;
	
	@Column(name="notecontent")
	private String notecontent;
	
	@Column(name="create_date")
	private Date createDate =new Date();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNotecontent() {
		return notecontent;
	}

	public void setNotecontent(String notecontent) {
		this.notecontent = notecontent;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}
