package com.customerService.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.customerService.model.Book;

// Its a book Service feign-client which takes care of calling the api's of Book-Service
@Service
@FeignClient(url = "http://localhost:8081/books", name = "Book-Client")
@ComponentScan
public interface BookClient {

	@GetMapping("/listBooks")
	public List<Book> getAllBooks(); // listBooks Api that returns the books

	@GetMapping("/getBook/{bookId}")
	public Book getBookById(@PathVariable("bookId") int bookId); // bet book by Id Api that returns the books

	@GetMapping("/checkBookQuantity/{bookId}")
	public Integer checkBookQuantity(@PathVariable("bookId") int bookId); // checkBookQuantity Api that returns the
																			// books

}
