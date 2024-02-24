package com.Book.store.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Book.store.UserPrincipal;
import com.Book.store.Dao.UserRegisterRepo;
import com.Book.store.model.user_register;
@Service
public class UserService implements UserDetailsService {
	@Autowired
	private UserRegisterRepo Urepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		user_register user=Urepo.findByName(username);
		if(user==null) {
			throw new UsernameNotFoundException("UserNotFound"+username);
		}
		return new UserPrincipal(user);
	}

	
}
