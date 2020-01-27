package com.app.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.AdminRecord;
import com.app.pojos.Customer;
import com.app.pojos.Orders;
import com.app.pojos.Products;
import com.app.pojos.Role;
import com.app.pojos.Status;

import com.app.pojos.Vendor;

@Repository
@Transactional
public class AdminDao implements IAdminDao
{
	@Autowired
	private SessionFactory sf;
	
	@Override
	public List<Vendor> vendorList() 
	{
		String jpql="select v from Vendor v";
		return sf.getCurrentSession().createQuery(jpql, Vendor.class).getResultList();
	}

	@Override
	public List<Customer> customerList() 
	{
		String jpql="select c from Customer c";
		return sf.getCurrentSession().createQuery(jpql, Customer.class).getResultList();
	}

	@Override
	public List<Products> productList() 
	{
		String jpql="select p from Products p";
		return sf.getCurrentSession().createQuery(jpql, Products.class).getResultList();
	}

	@Override
	public List<AdminRecord> paymentDetialsForVendor() 
	{
		String jpql="select ar from AdminRecord ar";
		return sf.getCurrentSession().createQuery(jpql, AdminRecord.class).getResultList();
	}


	@Override
	public List<Orders> profitOrLoss() 
	{
		String jpql="select o from Orders o";
		return sf.getCurrentSession().createQuery(jpql, Orders.class).getResultList();
	}

	@Override
	public String permitVendor(int vendorId) 
	{
		String msg="not permitted";
		Vendor v=sf.getCurrentSession().get(Vendor.class, vendorId);
		if(v!=null)
		{
			v.setStatus(Status.ACTIVE);
			msg="vendor permitted";

		}
		return msg;
	}

}
