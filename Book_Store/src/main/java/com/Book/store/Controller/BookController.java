package com.Book.store.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.Book.store.Service.BookService;
import com.Book.store.Service.UserRegoService;
import com.Book.store.model.books;
import com.Book.store.model.user_register;

@Controller
//@RequestMapping("/BController")
public class BookController {
	@Autowired
	private BookService BService;
//	@Autowired
//	private MyBookService MyBService;
	@Autowired
	private UserRegoService Uservice;
	
	
	@RequestMapping("/")
	public String Index() {
		return "index";
	}
	@RequestMapping("/Home")
	public String Home(HttpSession session) {
		
//		session.setAttribute(userID, )
		return "redirect:/profile";
	}
	
	@GetMapping("/admin/book_register")
	public String bookRegister() {
		return "bookRegister";
	}
	
	@GetMapping("/available_books")
	public ModelAndView getAllBook() {
		List<books>list=BService.getAllBook();

		return new ModelAndView("bookList","book",list);
	}
	
	@PostMapping("/save")
	public String addBook(@ModelAttribute books b) {
		BService.SaveBook(b);
		return "redirect:/available_books";
	}
	@RequestMapping("/mylist/{id}")
	public String addToMyBooks(@PathVariable("id") int id, HttpSession session) {
	    Integer userId = (Integer) session.getAttribute("userId");
	    if (userId != null) {
	        user_register user = Uservice.getUserById(userId);
	        if (user != null) {
	            books book = BService.getBook(id);
	            if (!user.getBook().contains(book)) {
	                user.getBook().add(book);
	                Uservice.updateUser(user);
	            }
	        }
	    }
	    return "redirect:/my_books";
	}

	
	
	@GetMapping("/my_books")
	public String viewMyBooks(Model model, HttpSession session) {
	    Integer userId = (Integer) session.getAttribute("userId");
	    if (userId != null) {
	        user_register user = Uservice.getUserById(userId);
	        if (user != null) {
	            List<books> myBooksList = user.getBook();  
	            model.addAttribute("book", myBooksList);  
	        }
	    }
	    return "MyBook";
	}

	@RequestMapping("/admin/editBook/{id}")
	public String EditBook(@PathVariable("id") int id,Model model) {
		books b=BService.EditMyBooks(id);
		model.addAttribute("book",b);
		return "Edit";
	}
	@RequestMapping("/admin/deleteBook/{id}")
	public String Delete(@PathVariable("id")int id) {
		BService.Delete(id);
		return "redirect:/available_books";
	}
	
	@RequestMapping("/deleteMyList/{id}")
	public String DeleteMyBook(@PathVariable("id") int id, HttpSession session) {
	    Integer userId = (Integer) session.getAttribute("userId");
	    if (userId != null) {
	        Uservice.deleteBookFromUser(id, userId);
	    }
	    return "redirect:/my_books";
	}
	
	
	}


