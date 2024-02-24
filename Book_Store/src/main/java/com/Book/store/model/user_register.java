package com.Book.store.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class user_register {
	
	


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String email;
	private String password;
	private String phone;
	private String role;
	@ManyToMany
	private List<books> book;
	
	
	
	
	public user_register() {
		this.role="ROLE_USER";
	
	}

	public user_register(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}
	
	public List<books> getBook() {
		return book;
	}

	public void setBook(List<books> book) {
		this.book = book;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	
	@Override
	public String toString() {
		return "user_register [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", phone="
				+ phone + "]";
	}

	
	
	
	

}
