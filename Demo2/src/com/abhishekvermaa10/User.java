package com.abhishekvermaa10;

import java.io.Serializable;

/**
 * @author abhishekvermaa10
 */
public class User implements Serializable {
	
	private int id;
	private String name;  
	private String password;
	private String platformName;

	public User(int id, String name, String password) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.platformName = "YouTube";
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", platformName=" + platformName + "]";
	}

}
