package com.app.dao;

import java.util.List;

import com.app.pojos.Cart;
import com.app.pojos.Customer;
import com.app.pojos.Orders;
import com.app.pojos.Products;

public interface ICustomerDao {
	
	String updateDetails(Customer customer);
	//String orderProduct

	Customer validateCustomer(String username);

	String addNewCustomer(Customer customer);

	Customer getCustomerByUsername(String username);
   String deleteCustomerDtls(Customer s);

  Products getProductById(int productId);

  String insertCartDetailsDao(Cart cart);

  List<Products> productList(String username);

String removeproduct(int productId);

String removeCartOfUser(String username);

String DecrementProductQuantity(int productId);

String insertOrderDetails(Orders orders);

}
