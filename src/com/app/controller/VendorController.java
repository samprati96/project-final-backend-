package com.app.controller;

import java.io.IOException;
import java.util.List;

import javax.xml.ws.spi.http.HttpContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;


import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.dao.IVendorDao;
import com.app.pojos.CategoryType;
import com.app.pojos.Image;
import com.app.pojos.Products;
import com.app.pojos.Vendor;

@RestController 
@CrossOrigin//(origins="http://localhost:4200")
@RequestMapping("/vendor")
public class VendorController 
{
	@Autowired
	private IVendorDao dao;
	
	
	@PostMapping("/addproduct/{vendor_id}")
	public ResponseEntity<?> addProductDetails(@RequestBody Products product,@PathVariable int vendor_id) {
		System.out.println("In addProductDetails :: product :" +product+"  vendorId  :" + vendor_id);
		try 
		{
			String msg=dao.addProductToDb( vendor_id, product);
			return new ResponseEntity<String>(msg, HttpStatus.OK);
			
		} 
		catch (RuntimeException e1) 
		{
			e1.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@GetMapping("/{username}")
	public ResponseEntity<?> listOfProducts(@PathVariable String username) {
		System.out.println("listOfProducts controller : username" + username);
		List<Products> list=dao.getProductsOfOneVendor(username);
		if (list == null)
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<Products>>(list, HttpStatus.OK);
	}
	
	

	
	
	@PostMapping("/registervendor")
	public ResponseEntity<?> addVendorDetails(@RequestBody Vendor vendor) 
	{
		String msg="";
		System.out.println("In addVendorDetails :: vendor :" +vendor);
		try 
		{
			msg=dao.addNewVendor(vendor);
			return new ResponseEntity<String>(msg, HttpStatus.OK);
			
		} 
		catch (RuntimeException e1) 
		{
			e1.printStackTrace();
			return new ResponseEntity<String>(msg,HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/validate/{username}")
	public ResponseEntity<?> validateVendor(@PathVariable String username) 
	{
		System.out.println("validateVendor controller : username" + username);
		Vendor v=dao.validateVendor(username);
		if (v == null)
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Vendor>(v, HttpStatus.OK);
	}

	
	
	
	
	@PostMapping(consumes = {"multipart/form-data"})
	public ResponseEntity<?> addProduct(@RequestParam int vendorId,@RequestParam String productName,@RequestParam double mrp,
										@RequestParam double cp,@RequestParam String description,
										@RequestParam String category,@RequestParam int quantity
										,@RequestParam MultipartFile uploadimage) throws IOException
	{
		
		System.out.println("in add product dtls :controller ");
		CategoryType c=CategoryType.valueOf(category);
		Products p = new Products(productName,mrp,cp,description,c,quantity);
		Image i = new Image();
		i.setImage(uploadimage.getBytes());
		try {
			
			if(uploadimage!=null)
			{
				System.out.println(uploadimage.getOriginalFilename());
					p.addImage(i);
					p.setStatus("AVAILABLE");
				Products p1=dao.addProduct(p,vendorId);
			return new ResponseEntity<Products>(p1, HttpStatus.CREATED);
			}
		}
		catch(RuntimeException e)
		{
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);	
		}
		return null;
	}
	
}


	


