package model;

import java.io.Serializable;

public class User implements Serializable {

	private int userId;
	private String mail;
	private String name;
	private String pass;
	private String place;
	private int isAdmin;
	
	public User(int userId, String mail, String name, String pass, String place, int isAdmin) {
		super();
		this.userId = userId;
		this.mail = mail;
		this.name = name;
		this.pass = pass;
		this.place = place;
		this.isAdmin = isAdmin;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public int getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	
}

