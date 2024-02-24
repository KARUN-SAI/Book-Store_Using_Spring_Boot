package com.Book.store.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Book.store.model.user_register;
@Repository
public interface UserRegisterRepo extends JpaRepository<user_register, Integer> {
	public Boolean existsByName(String name);
	
	public user_register findByName(String name);

	

}
