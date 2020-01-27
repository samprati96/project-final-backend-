package com.app.pojos;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class AdminRecord 
{
  private Integer historyId;
  private double totalPayment;
  @JsonIgnore
  private Vendor vendor;
  
  public AdminRecord() {
	// TODO Auto-generated constructor stub
}

public AdminRecord(double totalPayment) {
	super();
	this.totalPayment = totalPayment;
}

@Id 
@GeneratedValue(strategy=GenerationType.IDENTITY)
public Integer getHistoryId() {
	return historyId;
}

public void setHistoryId(Integer historyId) {
	this.historyId = historyId;
}

public double getTotalPayment() {
	return totalPayment;
}

public void setTotalPayment(double totalPayment) {
	this.totalPayment = totalPayment;
}
@OneToOne(mappedBy="adminRecord",cascade=CascadeType.ALL,orphanRemoval = true)
public Vendor getVendor() {
	return vendor;
}

public void setVendor(Vendor vendor) {
	this.vendor = vendor;
}

@Override
public String toString() {
	return "AdminRecord [totalPayment=" + totalPayment + "]";
}
  
  
  
}
