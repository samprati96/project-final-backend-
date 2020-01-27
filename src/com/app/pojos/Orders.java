package com.app.pojos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Orders 
{
 private Integer orderNo;
 private String username;
 private double amount;
 
 public Orders() {
	// TODO Auto-generated constructor stub
}

public Orders(String username, double amount) {
	super();
	this.username = username;
	this.amount = amount;
}

@Id 
@GeneratedValue(strategy=GenerationType.IDENTITY)
public Integer getOrderNo() {
	return orderNo;
}

public void setOrderNo(Integer orderNo) {
	this.orderNo = orderNo;
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public double getAmount() {
	return amount;
}

public void setAmount(double amount) {
	this.amount = amount;
}

@Override
public String toString() {
	return "Orders [username=" + username + ", amount=" + amount + "]";
}
 


}
