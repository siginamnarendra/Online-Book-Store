package com.bookService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bookService.dao.BookRepository;
import com.bookService.entity.Book;
import com.bookService.response.RequestResponse;
import com.customerService.exceptions.BookIdNotFoundException;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	public ResponseEntity<RequestResponse> getAllBooks() {
		RequestResponse resp = RequestResponse.builder()
				.message("Got the List of Books")
				.success(true).status(HttpStatus.FOUND).result(bookRepository.findAll()).build();
		return new ResponseEntity<>(resp, HttpStatus.FOUND);
		//return bookRepository.findAll();
	}

	public Book getBookById(int id) {
		return bookRepository.findById(id).orElseThrow(() -> new BookIdNotFoundException("Book not found with id: " + id));
	}

	public Book updateBookQuantity(int id) {
		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new BookIdNotFoundException("TO Update Book not found with id: " + id));
		
		int bookCount = book.getBookStockQuantity();
		book.setBookStockQuantity(--bookCount);
		return bookRepository.save(book);
	}

	public Integer checkBookQuantity(int id) {
		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new BookIdNotFoundException("TO update Book Quantity Bookid not found with id: " + id));
		return book.getBookStockQuantity();
		//return book.getBookStockQuantity();

	}
}
