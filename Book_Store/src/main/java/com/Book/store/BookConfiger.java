package com.Book.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.Book.store.Service.UserService;

@Configuration
@EnableWebSecurity
public class BookConfiger extends WebSecurityConfigurerAdapter{
	


	@Autowired
	private UserService Uservice;
	

	@Bean
	public AuthenticationProvider authenticateProvider() {
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setUserDetailsService(Uservice);
		provider.setPasswordEncoder(NoOpPasswordEncoder .getInstance());
		return provider;
	}


	@Override
	 protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/user_register").permitAll()
            .antMatchers("/images/**").permitAll()
            .antMatchers("/css/**").permitAll() // Allow access to all resources under /css
            .antMatchers("/register").permitAll()
            .antMatchers("/login").permitAll()
            .antMatchers("/admin/**").hasRole("ADMIN")
            .antMatchers("/").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login").permitAll()
            .defaultSuccessUrl("/Home", true)
            .and()
            .logout().invalidateHttpSession(true)
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/").permitAll();
    }

}
