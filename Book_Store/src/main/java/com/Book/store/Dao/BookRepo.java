package com.Book.store.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Book.store.model.books;
@Repository
public interface BookRepo extends JpaRepository<books, Integer>{

}
