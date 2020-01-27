package com.app.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.app.pojos.Cart;
import com.app.pojos.Customer;
import com.app.pojos.Orders;
import com.app.pojos.Products;
import com.app.pojos.Role;
import com.app.pojos.Status;
import com.app.pojos.Vendor;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;


@Repository
@Transactional
public class CustomerDao implements ICustomerDao 
{
	@Autowired
    private SessionFactory sf;
	
	@Override
	public String updateDetails(Customer customer) 
	{
		String msg="Customer details updation failed!!";
        if(customer!=null)
        {
		Customer c=getCustomerByUsername(customer.getUsername());
		c.setName(customer.getName());
		c.setPhone(customer.getPhone());
		c.setAddress(customer.getAddress());
		msg="Customer details updated successfully!!";
	
        }
		
        return msg;
        
	}
	
	
	@Override
	public Customer validateCustomer(String username) 
	{
		System.out.println(username);
		String jpql="select m from Customer m where m.username=:uname";
		Customer v=sf.getCurrentSession().createQuery(jpql, Customer.class).setParameter("uname", username).getSingleResult();
		System.out.println("Customer object :"+v);
        return v;
		
		
	   
	}


	@Override
	public String addNewCustomer(Customer customer) 
	{
		String msg="Insertion failed";
		
		if(customer!=null)
		{
			customer.setRole(Role.CUSTOMER);
			customer.setStatus(Status.ACTIVE);
			sf.getCurrentSession().persist(customer);
			msg="Insertion Successful";
		}
		
		return msg;
	}


	@Override
	public Customer getCustomerByUsername(String username) {
		System.out.println(username);
		String jpql="select m from Customer m where m.username=:uname";
		Customer v=sf.getCurrentSession().createQuery(jpql, Customer.class).setParameter("uname", username).getSingleResult();
		System.out.println("Customer object :"+v);
        return v;

	}


	@Override
	public String deleteCustomerDtls(Customer s) 
	{
		String msg="deletion failed";
		
		if(s!=null)
		{
			sf.getCurrentSession().delete(s);
			msg="deletion successful";

		}
		
		return msg;
	}


	@Override
	public Products getProductById(int productId) {
		System.out.println(productId);
		String jpql="select m from Products m where m.productId=:pid";
		Products v=sf.getCurrentSession().createQuery(jpql, Products.class).setParameter("pid", productId).getSingleResult();
		System.out.println("Products object :"+v);
        return v;
	}


	@Override
	public String insertCartDetailsDao(Cart cart) 
	{
		String msg="insertion into cart failed";
		if(cart!=null)
		{
		  sf.getCurrentSession().persist(cart);
		  msg="insertion into cart :Successful";
		}
		return msg;
	}


	@Override
	public List<Products> productList(String username) 
	{
		List<Products> productList=new ArrayList<Products>();
		List<Cart> cartlist=getProductIdsForCart(username);
		int id;
		for(int i=0;i<cartlist.size();i++)
		{
			id=cartlist.get(i).getProductId();
			System.out.println("id ["+i+"]  :"+id);
			productList.add(sf.getCurrentSession().get(Products.class,id));
		}
		
		
		return productList;
	}


	private List<Cart> getProductIdsForCart(String username) 
	{
		String jpql="select c from Cart c where username=:un";
		
		return sf.getCurrentSession().createQuery(jpql, Cart.class).setParameter("un", username).getResultList();
	}


	@Override
	public String removeproduct(int productId) 
	{
		int count=0;
		String msg="removeproduct: failed";

		String jpql="select c from Cart c where productId=:pid";
		
		List<Cart> cartList=sf.getCurrentSession().createQuery(jpql, Cart.class).setParameter("pid", productId).getResultList();
		if(cartList.size()!=0)
		{
			for(int i=0;i<cartList.size();i++)
			{
				if(count==1)
				{
					break;
				}
				sf.getCurrentSession().delete(cartList.get(i));
				count++;

			}
		msg="removeproduct: successful";
		}
		return msg;
	}


	@Override
	public String removeCartOfUser(String username) 
	{
		String msg="removeCartOfUser: failed";

		String jpql="select c from Cart c where username=:un";
		List<Cart> cartList=sf.getCurrentSession().createQuery(jpql, Cart.class).setParameter("un", username).getResultList();

		if(cartList.size()!=0)
		{
			for(int i=0;i<cartList.size();i++)
			{
			DecrementProductQuantity(cartList.get(i).getProductId());
			sf.getCurrentSession().delete(cartList.get(i));
			}
	    	msg="removeCartOfUser: successful";
		}

		
		return msg;
	}


	@Override
	public String DecrementProductQuantity(int productId) 
	{
	String msg="DecrementProductQuantity: failed";

	Products p=	sf.getCurrentSession().get(Products.class,productId);
			if(p!=null)
			{
				p.setQuantity(p.getQuantity()-1);
				
				msg="DecrementProductQuantity: failed";
				
			}
		return msg;
	}


	@Override
	public String insertOrderDetails(Orders orders) 
	{
		String msg="insertOrderDetails failed";
		if(orders!=null)
		{
		  sf.getCurrentSession().persist(orders);
		  msg="insertOrderDetails :Successful";
		}
		return msg;

	}


}
