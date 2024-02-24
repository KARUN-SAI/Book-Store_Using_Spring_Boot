package com.Book.store.Controller;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.Book.store.Service.UserRegoService;
import com.Book.store.model.user_register;



@Controller
public class UserLogin {
    @Autowired
    UserRegoService Uservice;

    @GetMapping("/user_register")
    public String userRegister() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute user_register user, HttpSession session) {
        boolean b = Uservice.checkExist(user.getName());
        if (b) {
            session.setAttribute("msg", "Username already exists");
        } else {
            user_register user1 = Uservice.RegisterUser(user);
           
            if (user1 != null) {
                session.setAttribute("msg", "User registration Success");
            } else {
                session.setAttribute("msg", "Something went wrong");
            }
        }
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login"; 
    }

    @GetMapping("/logout")
    public String logoutUser() {
       
        return "redirect:/";
    }
    
    @GetMapping("/profile")
    public String userProfile(Model model, HttpSession session) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();
            Collection<? extends GrantedAuthority> role=userDetails.getAuthorities();
            user_register user = Uservice.checkLogin(new user_register(username, null));
            if (user != null) {
               int userId = user.getId();
                session.setAttribute("userId", userId);
                session.setAttribute("username", username);
                session.setAttribute("role",role);
                model.addAttribute("userId", userId);
            }
        }

        return "home";  
    }
}
