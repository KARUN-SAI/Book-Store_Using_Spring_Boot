package com.Book.store.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Book.store.Dao.UserRegisterRepo;
import com.Book.store.model.books;
import com.Book.store.model.user_register;

@Service
public class UserRegoService {
    @Autowired
    private UserRegisterRepo Urepo;

    public user_register RegisterUser(user_register user) {
        return Urepo.save(user);
    }

    public boolean checkExist(String name) {
        return Urepo.existsByName(name);
    }

    public user_register checkLogin(user_register user) {
        return Urepo.findByName(user.getName());
    }

    public user_register getUserById(int id) {
        Optional<user_register> userOptional = Urepo.findById(id);
        return userOptional.orElse(null);
    }

    public user_register updateUser(user_register user) {
        return Urepo.save(user);
    }
    public void deleteBookFromUser(int bookId, int userId) {
        Optional<user_register> optionalUser = Urepo.findById(userId);
        if (optionalUser.isPresent()) {
            user_register user = optionalUser.get();
            List<books> userBooks = user.getBook();
            userBooks.removeIf(book -> book.getId() == bookId);
            Urepo.save(user);
        }
    }
}