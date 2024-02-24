package com.Book.store.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class books {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String author;
	private String price;
	
	@ManyToMany(mappedBy = "book")
	private List<user_register> user;
	
	
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
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public List<user_register> getUser() {
		return user;
	}
	public void setUser(List<user_register> user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "books [id=" + id + ", name=" + name + ", author=" + author + ", price=" + price + "]";
	}

}
