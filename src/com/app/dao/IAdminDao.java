package com.app.dao;

import java.util.List;

import com.app.pojos.AdminRecord;
import com.app.pojos.Customer;
import com.app.pojos.Orders;
import com.app.pojos.Products;
import com.app.pojos.Vendor;

public interface IAdminDao 
{
	List<Vendor> vendorList();
	List<Customer> customerList();
	List<Products> productList();
	List<AdminRecord> paymentDetialsForVendor();
	List<Orders> profitOrLoss();
	String permitVendor(int vendorId);
	
}
