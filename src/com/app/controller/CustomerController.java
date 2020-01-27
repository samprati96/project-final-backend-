package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.ICustomerDao;
import com.app.pojos.Cart;
import com.app.pojos.Customer;
import com.app.pojos.Orders;
import com.app.pojos.Products;
import com.app.pojos.Vendor;

@RestController 
@CrossOrigin
@RequestMapping("/customer")
public class CustomerController 
{
	@Autowired
	private ICustomerDao dao;
	
	@PutMapping
	public ResponseEntity<?> updateCustomerDetails(@RequestBody Customer customer) 
	{
		
		String msg="Updation failed";
		System.out.println("In updateCustomerDetails :: customer :" +customer);
		try 
		{   
			msg=dao.updateDetails(customer);
			return new ResponseEntity<String>(msg, HttpStatus.OK);
			
		} 
		catch (RuntimeException e1) 
		{
			e1.printStackTrace();
			return new ResponseEntity<String>(msg,HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@PostMapping("/registercustomer")
	public ResponseEntity<?> addCustomerDetails(@RequestBody Customer customer) 
	{
		String msg="";
		System.out.println("In addCustomerDetails :: customer :" +customer);
		try 
		{
			msg=dao.addNewCustomer(customer);
			return new ResponseEntity<String>(msg, HttpStatus.OK);
			
		} 
		catch (RuntimeException e1) 
		{
			e1.printStackTrace();
			return new ResponseEntity<String>(msg,HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}


	
	
	@GetMapping("/validate/{username}")
	public ResponseEntity<?> validateCustomer(@PathVariable String username) 
	{
		
		System.out.println("username  :"+username);
		try 
		{
			Customer v=dao.validateCustomer(username);
			return new ResponseEntity<Customer>(v, HttpStatus.OK);
			
		} 
		catch (RuntimeException e1) 
		{
			e1.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@DeleteMapping("/{username}") // delete existing resource
	public ResponseEntity<String> cancelCustomer(@PathVariable String username) {
		System.out.println("rest srvr : delete Customer info " + username);
		try {
			Customer s = dao.getCustomerByUsername(username);
			if (s != null)
				return new ResponseEntity<String>(dao.deleteCustomerDtls(s), HttpStatus.OK);
			else
				return new ResponseEntity<>("Customer deletion failed : ", HttpStatus.NOT_FOUND);
		} catch (RuntimeException e) {
			System.out.println("err in deletion " + e);
			return new ResponseEntity<>("Customer deletion failed : " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@GetMapping("/product/{productId}")
	public ResponseEntity<?> getProduct(@PathVariable int productId) 
	{
		
		System.out.println("productId  :"+productId);
		try 
		{
			Products v=dao.getProductById(productId);
			System.out.println(v);
			return new ResponseEntity<Products>(v, HttpStatus.OK);
			
		} 
		catch (RuntimeException e1) 
		{
			e1.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
	
	@PostMapping("/cart")
	public ResponseEntity<?> insertCartDetails(@RequestBody Cart cart) 
	{
		
		System.out.println("insertCartDetails:controller  :"+cart);
		try 
		{
			String msg=dao.insertCartDetailsDao(cart);
			System.out.println(msg);
			return new ResponseEntity<String>(msg, HttpStatus.OK);
			
		} 
		catch (RuntimeException e1) 
		{
			e1.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
	@GetMapping("/cart/{username}")
	public ResponseEntity<?> listProducts(@PathVariable String username)
	{
		System.out.println("in list products customer controller");
		List<Products> allProducts=dao.productList(username);
		if(allProducts.size()==0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);	
			
		return new ResponseEntity<List<Products>>(allProducts,HttpStatus.OK);
	}

	
	@DeleteMapping("/remove/{productId}")
	public ResponseEntity<?> removeProduct(@PathVariable int productId)
	{
		System.out.println("removeProduct : controller");
		String msg=dao.removeproduct(productId);
			
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}

	@DeleteMapping("/removeall/{username}")
	public ResponseEntity<?> removeCartOfUser(@PathVariable String username)
	{
		System.out.println("removeCartOfUser : controller");
		String msg=dao.removeCartOfUser(username);
			
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}


	@GetMapping("/decrement/{productId}")
	public ResponseEntity<?> DecrementProductQuantity(@PathVariable int productId)
	{
		System.out.println("DecrementProductQuantity controller");
		String msg=dao.DecrementProductQuantity(productId);
			
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}

	@PostMapping("/order")
	public ResponseEntity<?> insertOrderDetails(@RequestBody Orders orders) 
	{
		
		System.out.println("insertOrderDetails :controller  :"+orders);
		try 
		{
			String msg=dao.insertOrderDetails(orders);
			System.out.println(msg);
			return new ResponseEntity<String>(msg, HttpStatus.OK);
			
		} 
		catch (RuntimeException e1) 
		{
			e1.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	


}
