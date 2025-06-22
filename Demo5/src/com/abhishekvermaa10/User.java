package com.abhishekvermaa10;

import java.io.Serializable;

/**
 * @author abhishekvermaa10
 */
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String userName;  
	private transient String password;
	private static String platformName;

	public User(int id, String userName, String password) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		platformName = "YouTube";
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", platformName=" + platformName + "]";
	}

}
