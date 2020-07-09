package com.stackroute.userservice.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

/**
 * @author Zaithoon M
 *
 */
@Entity
public class User {

	@Id
	private String userid;
	
	private String firstname;
	
	private String lastname;
	
	private String password;
	
	@CreationTimestamp
	private Date created;

	public User() {
		super();
	}
	
	public User(String userid, String firstname, String lastname, String password, Date created) {
		super();
		this.userid = userid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.created = created;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "User [userid=" + userid + ", firstname=" + firstname + ", lastname=" + lastname + ", password="
				+ password + ", created=" + created + "]";
	}

	
	
}
