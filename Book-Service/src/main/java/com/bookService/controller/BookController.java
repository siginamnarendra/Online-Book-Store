package com.bookService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookService.entity.Book;
import com.bookService.response.RequestResponse;
import com.bookService.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookService bookService;

	@GetMapping("/listBooks")
	public ResponseEntity<RequestResponse> getBooks(@RequestHeader("loggedInUserName") String userName) {
		return bookService.getAllBooks();

	}

	@GetMapping("/getBook/{bookId}")
	public Book getBookById(@PathVariable("bookId") int bookId) {

		return bookService.getBookById(bookId);
	}

	@GetMapping("/updateBook/{bookId}")
	public Book updateBookQuantity(@PathVariable("bookId") int bookId) {

		return bookService.updateBookQuantity(bookId);
	}

	@GetMapping("/checkBookQuantity/{bookId}")
	public Integer  checkBookQuantity(@PathVariable("bookId") int bookId) {

		return bookService.checkBookQuantity(bookId);
	}
}
