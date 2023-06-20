package com.cartService.service;



import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cartService.client.BookClient;
import com.cartService.dao.CartItemsRepository;
import com.cartService.dao.CartRepository;
import com.cartService.dto.Book;
import com.cartService.entity.Cart;
import com.cartService.entity.CartItems;
import com.cartService.response.RequestResponse;
import com.customerService.exceptions.QuantityNotAvailableException;

@Service
public class CartService {

	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private CartItemsRepository cartitemsRepository;
	@Autowired
	private BookClient bookClient;



	public ResponseEntity<RequestResponse> saveToCart(int bookId, int quantity, String customerId) {
		try {
			Cart crt = getCartById(customerId);
			Book book = bookClient.getBookById(bookId);
			if (isBookAvailabe(book.getBookStockQuantity(), quantity)) {
				CartItems cartItems = new CartItems();
				cartItems.setBookId(book.getBookId());
				cartItems.setBookPrice(book.getBookPrice());
				cartItems.setBookTitle(book.getBookTitle());
				cartItems.setQuantity(quantity);
				cartItems.setCart(crt);
				crt.getCartItems().add(cartItems);
				
				RequestResponse resp = RequestResponse.builder()
						.message("Book Added to cart")
						.success(true).status(HttpStatus.FOUND).result(cartRepository.save(crt)).build();
				return new ResponseEntity<>(resp, HttpStatus.FOUND);
			}else {
				throw new QuantityNotAvailableException("quantitynot available! available quantity is =" + book.getBookStockQuantity());
			} 
		} catch (NullPointerException ex) {
			int bookQuantity = bookClient.checkBookQuantity(bookId);
			if (isBookAvailabe(bookQuantity, quantity)) {

				Book book = bookClient.getBookById(bookId);
				Cart cart = new Cart();
				cart.setCustomerId(customerId);
				CartItems cartItems = new CartItems();
				cartItems.setBookId(book.getBookId());
				cartItems.setBookPrice(book.getBookPrice());
				cartItems.setBookTitle(book.getBookTitle());
				cart.getCartItems().add(cartItems);
				cartItems.setCart(cart);
				cartItems.setQuantity(quantity);

				
				RequestResponse resp = RequestResponse.builder()
						.message("Book Added to cart")
						.success(true).status(HttpStatus.CREATED).result(cartRepository.save(cartRepository.save(cart))).build();
				return new ResponseEntity<>(resp, HttpStatus.CREATED);
				//return "item added to cart";
			} else {
				throw new QuantityNotAvailableException("please select the <= available book quantity");
			}
		}

	}

	public boolean isBookAvailabe(int availableQuantity1, int orderedQuantity) {
		return availableQuantity1 >= orderedQuantity;
	}

	public Cart getCartById(String customerId) {
		return cartRepository.findByCustomerId(customerId);
	}

	public String deleteFromCart(int cartItemId) {
		cartitemsRepository.deleteById(cartItemId);
		return "deleted id" + cartItemId;
	}

	public Cart getCartItems(String customerId) {
		Cart cart = new Cart();
		try {
			cart = cartRepository.findById(customerId).get();
//			RequestResponse resp = RequestResponse.builder()
//					.message("Got the Cart Items")
//					.success(true).status(HttpStatus.CREATED).result(cartRepository.findById(customerId).get()).build();
//			return new ResponseEntity<>(resp, HttpStatus.CREATED);
			return cart;
		} catch (RuntimeException e) {
//			RequestResponse resp = RequestResponse.builder()
//					.message("Got the Cart Items")
//					.success(true).status(HttpStatus.CREATED).result(cart).build();
//			return new ResponseEntity<>(resp, HttpStatus.CREATED);
			return cart;
		}

	}

}
