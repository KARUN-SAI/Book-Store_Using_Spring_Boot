package com.Book.store.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Book.store.Dao.BookRepo;
//import com.Book.store.model.MyBooks;
import com.Book.store.model.books;
@Service
public class BookService {
	@Autowired
	private BookRepo repo;

	public void SaveBook(books b) {
		repo.save(b);
		
		
	}

	public List<books> getAllBook(){
		return repo.findAll();
	}

	public books getBook(int id) {
		
		return repo.findById(id).get();
	}

	public books EditMyBooks(int id) {
		
		return repo.findById(id).get();
	}



	public void Delete(int id) {
		repo.deleteById(id);
		
	}

	

	
	

}
