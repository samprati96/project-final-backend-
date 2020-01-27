package com.app.pojos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.app.pojos.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class Vendor {
	private Integer vendorId;
	private String name;
	private String phone;
	private Status status;
	private String address;
	
	private String username;
	private String password;
	private Role role=Role.VENDOR;
	
	@JsonIgnore
	private List<Products> products=new ArrayList<Products>();
	@JsonIgnore
	private AdminRecord adminRecord;
	

	
	public Vendor() {
		// TODO Auto-generated constructor stub
	}
    //Helper method
	public void addProduct(Products product)
    {
    	products.add(product);
    	product.setVendor(this);
    }
    //Helper method
	public void removeProduct(Products product)
    {
    	products.remove(product);
    	product.setVendor(null);
    }


	public Vendor(String name, String phone, Status status,String address) {
		super();
		this.name = name;
		this.phone = phone;
		this.status = status;
		this.address=address;
	}
	
	
	


	public Vendor(String name, String phone, Status status, String address, String username, String password,
			Role role) {
		super();
		this.name = name;
		this.phone = phone;
		this.status = status;
		this.address = address;
		this.username = username;
		this.password = password;
		this.role = role;
	}
	public Vendor(Integer vendorId, String name, String phone, Status status) {
		super();
		this.vendorId = vendorId;
		this.name = name;
		this.phone = phone;
		this.status = status;
	}
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getVendorId() {
		return vendorId;
	}



	public void setVendorId(Integer vendorId) {
		this.vendorId = vendorId;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}


	@Enumerated(EnumType.STRING)
	public Status getStatus() {
		return status;
	}



	public void setStatus(Status status) {
		this.status = status;
	}


 
	@OneToMany(mappedBy = "vendor",cascade=CascadeType.ALL,orphanRemoval = true)
	public List<Products> getProducts() {
		return products;
	}



	public void setProducts(List<Products> products) {
		this.products = products;
	}


	


	
    @OneToOne
	public AdminRecord getAdminRecord() {
		return adminRecord;
	}



	public void setAdminRecord(AdminRecord adminRecord) {
		this.adminRecord = adminRecord;
	}

	

	

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Enumerated(EnumType.STRING)
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Vendor [name=" + name + ", phone=" + phone + ", status=" + status + "]";
	}
	
	
	
}
