package com.app.pojos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Address 
{
private Integer addressId;
private String pincode;
private String city;
private String state;
private String address;

@JsonIgnore
private Customer customer;


public Address() 
{  }



public Address(String pincode, String city, String state, String address) {
	super();
	this.pincode = pincode;
	this.city = city;
	this.state = state;
	this.address = address;
}



@Id 
@GeneratedValue(strategy=GenerationType.IDENTITY)
public Integer getAddressId() {
	return addressId;
}



public void setAddressId(Integer addressId) {
	this.addressId = addressId;
}



public String getPincode() {
	return pincode;
}

public void setPincode(String pincode) {
	this.pincode = pincode;
}

public String getCity() {
	return city;
}

public void setCity(String city) {
	this.city = city;
}

public String getState() {
	return state;
}

public void setState(String state) {
	this.state = state;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}



@ManyToOne
@JoinColumn(name = "cust_id")
public Customer getCustomer() {
	return customer;
}



public void setCustomer(Customer customer) {
	this.customer = customer;
}



@Override
public String toString() {
	return "Address [pincode=" + pincode + ", city=" + city + ", state=" + state + ", address=" + address + "]";
}



}
