package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.IAdminDao;
import com.app.pojos.AdminRecord;
import com.app.pojos.Customer;
import com.app.pojos.Orders;
import com.app.pojos.Products;
import com.app.pojos.Vendor;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController 
{
	@Autowired
	private IAdminDao dao;
	
	public AdminController()
	{
		System.out.println("in admin controller...");
	}
	
	@GetMapping("/vendors")
	public ResponseEntity<?> listVendors() 
	{
		System.out.println("in list vendors");
		
		List<Vendor> allVendors = dao.vendorList();
		if (allVendors.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		
		return new ResponseEntity<List<Vendor>>(allVendors, HttpStatus.OK);
	}
	
	@GetMapping("/customers")
	public ResponseEntity<?> listCustomers() 
	{
		System.out.println("in list customers");
		
		List<Customer> allCustomers = dao.customerList();
		if (allCustomers.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		
		return new ResponseEntity<List<Customer>>(allCustomers, HttpStatus.OK);
	}
	
	@GetMapping("/products")
	public ResponseEntity<?> listProducts()
	{
		System.out.println("in list products");
		List<Products> allProducts=dao.productList();
		if(allProducts.size()==0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);	
			
		return new ResponseEntity<List<Products>>(allProducts,HttpStatus.OK);
	}
	
	@GetMapping("/adminRecord")
	public ResponseEntity<?> listAdminRecord()
	{
		System.out.println("in list adminRecord");
		List<AdminRecord> allAdminRecords=dao.paymentDetialsForVendor();
		if(allAdminRecords.size()==0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);	
			
		return new ResponseEntity<List<AdminRecord>>(allAdminRecords,HttpStatus.OK);
	}
	
	
	@GetMapping("/orders")
	public ResponseEntity<?> listOrders()
	{
		System.out.println("in list orders");
		List<Orders> allOrders=dao.profitOrLoss();
		if(allOrders.size()==0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);	
			
		return new ResponseEntity<List<Orders>>(allOrders,HttpStatus.OK);
	}
	
	@GetMapping("/{vendorId}")
	public ResponseEntity<?> permitVendor(@PathVariable int vendorId)
	{
		String msg="Permission denied";
		try 
		{
			msg=dao.permitVendor(vendorId);
			return new ResponseEntity<String>(msg, HttpStatus.OK);
			
		} 
		catch (RuntimeException e1) 
		{
			e1.printStackTrace();
			return new ResponseEntity<String>(msg,HttpStatus.INTERNAL_SERVER_ERROR);
		}	
		
	}
	
}
