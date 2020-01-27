package com.app.pojos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Products 
{
 private Integer productId;
 private String productName;
 private double mrp;
 private double cp;
 private String description;
 private CategoryType category;
 private int quantity;
 private String status;
 @JsonIgnore
 private byte[] images;
 private List<Image> image=new ArrayList<Image>();
 
 @JsonIgnore
 private Vendor vendor;
 
 public Products() {
	// TODO Auto-generated constructor stub
}
 
    public void addImage(Image a)
	{
		image.add(a);
		a.setProduct(this);
	}
	public void removeImage(Image a)
	{
		image.remove(a);
		a.setProduct(null);
	}
 
	

public Products(String productName, double mrp, double cp, String description, CategoryType category, int quantity) {
		super();
		this.productName = productName;
		this.mrp = mrp;
		this.cp = cp;
		this.description = description;
		this.category = category;
		this.quantity = quantity;
	}

@Id 
@GeneratedValue(strategy=GenerationType.IDENTITY)
public Integer getProductId() {
	return productId;
}

public void setProductId(Integer productId) {
	this.productId = productId;
}

public String getProductName() {
	return productName;
}

public void setProductName(String productName) {
	this.productName = productName;
}

public double getMrp() {
	return mrp;
}

public void setMrp(double mrp) {
	this.mrp = mrp;
}

public double getCp() {
	return cp;
}

public void setCp(double cp) {
	this.cp = cp;
}

@Enumerated(EnumType.STRING)
public CategoryType getCategory() {
	return category;
}

public void setCategory(CategoryType category) {
	this.category = category;
}
@OneToMany(mappedBy = "product",cascade=CascadeType.ALL,orphanRemoval = true,fetch =FetchType.EAGER)
@Fetch(FetchMode.SUBSELECT)
public List<Image> getImage() {
	return image;
}

public void setImage(List<Image> image) {
	this.image = image;
}
@ManyToOne
@JoinColumn(name="vendor_id")
public Vendor getVendor() {
	return vendor;
}

public void setVendor(Vendor vendor) {
	this.vendor = vendor;
}



public int getQuantity() {
	return quantity;
}

public void setQuantity(int quantity) {
	this.quantity = quantity;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}



public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}



@Lob
public byte[] getImages() {
	return images;
}



public void setImages(byte[] images) {
	this.images = images;
}



@Override
public String toString() {
	return "Product [productName=" + productName + ", mrp=" + mrp + ", cp=" + cp + ", desc=" + description + ", category="
			+ category + "]";
}
 
 
 
 
}
