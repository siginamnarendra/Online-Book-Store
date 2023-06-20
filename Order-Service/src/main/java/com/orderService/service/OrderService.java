package com.orderService.service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.orderService.response.RequestResponse;
import com.customerService.exceptions.QuantityNotAvailableException;
import com.orderService.client.BookClient;
import com.orderService.client.CartClient;
import com.orderService.dao.OrderJpaRepository;
import com.orderService.entity.OrderItems;
import com.orderService.entity.Orders;
import com.orderService.model.Cart;
import com.orderService.model.CartItems;

@Service
public class OrderService {

	@Autowired
	private OrderJpaRepository orderRepository;
	@Autowired
	private CartClient cartClient;
	@Autowired
	private BookClient bookClient;

	public ResponseEntity<RequestResponse> getOrdersByCustomer(String customerId) {
		RequestResponse resp = RequestResponse.builder().message("Found all orders").success(true)
				.status(HttpStatus.CREATED).result(orderRepository.findAll()).build();
		return new ResponseEntity<>(resp, HttpStatus.CREATED);
	}

	public ResponseEntity<RequestResponse> getOrderById(String OrderId) {
		Orders order = new Orders();
		try {
			order = orderRepository.findById(OrderId).get();
			RequestResponse resp = RequestResponse.builder().message("Found the order").success(true)
					.status(HttpStatus.CREATED).result(order).build();
			return new ResponseEntity<>(resp, HttpStatus.CREATED);
		} catch (RuntimeException e) {
			RequestResponse resp = RequestResponse.builder().message("No orders placed Yet").success(true)
					.status(HttpStatus.FOUND).result(order).build();
			return new ResponseEntity<>(resp, HttpStatus.FOUND);
		}
	}

	public ResponseEntity<RequestResponse> addFromCart(String customerId) {
		Cart cart = cartClient.getCart(customerId);
		System.err.println(cart);

		List<CartItems> items = cart.getCartItems();

		Orders order = new Orders();

		String randomOrderId = UUID.randomUUID().toString();

		order.setOrderId(randomOrderId);
		order.setCustomerId(cart.getCustomerId());

		Iterator<CartItems> iterator = items.iterator();
		while (iterator.hasNext()) {
			CartItems item = iterator.next();
			if (bookClient.checkBookQuantity(item.getBookId()) >= item.getQuantity()) {
				OrderItems oi = new OrderItems();
				oi.setBookId(item.getBookId());
				oi.setBookPrice(item.getBookPrice());
				oi.setBookTitle(item.getBookTitle());
				oi.setCartItemId(item.getCartItemId());
				oi.setQuantity(item.getQuantity());
				oi.setCustomerId(item.getCustomerId());
				oi.setOrderDate(new Date());
				oi.setCustomerId(customerId);

				oi.setOrders(order);
				order.getOrderItems().add(oi);
				for (int i = 0; i < item.getQuantity(); i++) {
					bookClient.updateBookQuantity(item.getBookId());
				}
				cartClient.deleteFromCart(item.getCartItemId());
			} else {
				throw new QuantityNotAvailableException("Book With Id " + item.getBookId()
						+ "'s Quantity Not available please Check the quantity before placing order ");

			}

		}
		RequestResponse resp = RequestResponse.builder().message("Order Placed").success(true)
				.status(HttpStatus.CREATED).result(orderRepository.save(order)).build();
		return new ResponseEntity<>(resp, HttpStatus.CREATED);
	}

}
