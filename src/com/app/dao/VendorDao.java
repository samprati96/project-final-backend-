package com.app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import org.hibernate.SessionFactory;

import com.app.pojos.Products;
import com.app.pojos.Role;
import com.app.pojos.Status;
import com.app.pojos.Vendor;

@Repository
@Transactional
public class VendorDao implements IVendorDao
{
	@Autowired
	private SessionFactory sf;

	@Override
	public String addProductToDb(int vendorId, Products product) 
	{
		Vendor vendor=sf.getCurrentSession().get(Vendor.class,vendorId);
		System.out.println( "vendor: "+vendor+" product: "+product+" vendorId :"+vendor.getVendorId());

		String msg="addProductToDb dao: FAILED!!";
		if(vendor!=null&&product!=null)
		{
		product.setStatus("AVAILABLE");
		vendor.addProduct(product);
		vendor.getProducts().size();
		sf.getCurrentSession().saveOrUpdate(vendor);
		

        msg="addProductToDb dao: SUCCESSFUL!!";
		}
		System.out.println(msg+" vendor:"+vendor+" product: "+product);
		return msg;
	}

	@Override
	public Vendor getVendor(int vendorId) 
	{
		return sf.getCurrentSession().get(Vendor.class, vendorId);
	}

	@Override
	public List<Products> getProductsOfOneVendor(String username) 
	{
		System.out.println("Inside getProductsOfOneVendor dao:: username :"+username);
		
		Vendor vendor=validateVendor(username);
		String jpql="select p from Products p where p.vendor=:vdr";
		return sf.getCurrentSession().createQuery(jpql, Products.class).setParameter("vdr", vendor).getResultList();
	}

	@Override
	public String addNewVendor(Vendor vendor) 
	{
		String msg="Adding Vendor failed";
		System.out.println("addNewVendor ::vendor :"+vendor);
		if(vendor!=null)
		{
		vendor.setRole(Role.VENDOR);
		vendor.setStatus(Status.ACTIVE);
		sf.getCurrentSession().persist(vendor);
		msg="Adding Vendor successful";
		}
		
		return msg;
	}

	@Override
	public Vendor validateVendor(String username) 
	{
		System.out.println(username);
		String jpql="select m from Vendor m where m.username=:uname";
		Vendor v=sf.getCurrentSession().createQuery(jpql, Vendor.class).setParameter("uname", username).getSingleResult();
		System.out.println("vendor object :"+v);
        return v;
		
		
	   
	}

	@Override
	public Products addProduct(Products p,int vendorId) {
		Vendor vendor=sf.getCurrentSession().get(Vendor.class,vendorId);
		p.setVendor(vendor);
		sf.getCurrentSession().persist(p);
		return p;
	}
	
	

}
