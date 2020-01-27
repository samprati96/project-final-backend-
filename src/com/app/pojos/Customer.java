
package com.app.pojos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.app.pojos.Address;
import com.app.pojos.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Customer {
	
	  private Integer customerId;
   	  private String name;
	  private String phone;
	  private String address;
	  
	  private Status status;

	  @JsonIgnore
	  private List<Address> addresslist=new ArrayList<Address>();
		private String username;
		private String password;
		private Role role=Role.CUSTOMER;

	  
	  
	  public Customer() {
		// TODO Auto-generated constructor stub
	}


	public Customer(String name, String phone, Status status) {
		super();
		this.name = name;
		this.phone = phone;
		this.status = status;
	}
	

	public Customer(String name, String phone, Status status, String username, String password, Role role) {
		super();
		this.name = name;
		this.phone = phone;
		this.status = status;
		this.username = username;
		this.password = password;
		this.role = role;
	}


	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getCustomerId() {
		return customerId;
	}


	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
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
	
	

	@OneToMany(mappedBy = "customer",cascade=CascadeType.ALL,orphanRemoval = true)
	public List<Address> getAddresslist() {
		return addresslist;
	}


	public void setAddresslist(List<Address> addresslist) {
		this.addresslist = addresslist;
	}


	
	


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	@Override
	public String toString() {
		return "Customer [name=" + name + ", phone=" + phone + ", status=" + status + "]";
	}
	  
	  
	  
	  

}
