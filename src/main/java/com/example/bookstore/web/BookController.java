package com.example.bookstore.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;
import com.example.bookstore.domain.CategoryRepository;

@Controller
public class BookController {
	
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CategoryRepository crepository;
	
	// Show all books
		@RequestMapping(value = {"/", "/booklist"})
		public String bookList(Model model) {
			model.addAttribute("books", repository.findAll());
			return "booklist";
		}
	
	// REST service to findAll
		@RequestMapping(value="/books", method = RequestMethod.GET)
		public @ResponseBody List<Book> bookListRest() {
			return (List<Book>) repository.findAll();
		}
		
		// Add new book
		@RequestMapping(value = "/add")
		public String addBook(Model model) {
			model.addAttribute("book", new Book());
			model.addAttribute("categorys", crepository.findAll());
			return "addbook";
		}
		// Save new book
		@RequestMapping(value = "/save", method = RequestMethod.POST)
		public String save(Book book) {
			repository.save(book);
			return "redirect:booklist";
		}
		
		// Delete a book
		@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
		public String deleteBook(@PathVariable("id") Long bookId, Model model) {
			repository.deleteById(bookId);
			return "redirect:../booklist";
			
		}
		// Edit a book
		@RequestMapping(value ="/edit/{id}")
		public String editBook(@PathVariable("id")Long bookId, Model model) {
			model.addAttribute("book", repository.findById(bookId));
			model.addAttribute("categorys", crepository.findAll());
			return "editBook";
		
		}
	
	@RequestMapping("/index")
	public String getBook(Model model) {
	
	return "index";
}

}
