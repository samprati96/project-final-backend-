package com.app.pojos;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Image 
{
 private Integer imageId;
 private byte[] image;
 private Products product;
 
  
 public Image() { }

public Image(byte[] image) {
	super();
	this.image = image;
}

@Id 
@GeneratedValue(strategy=GenerationType.IDENTITY)
public Integer getImageId() {
	return imageId;
}

public void setImageId(Integer imageId) {
	this.imageId = imageId;
}

@Lob 
public byte[] getImage() {
	return image;
}

public void setImage(byte[] image) {
	this.image = image;
}
@JsonIgnore
@ManyToOne
@JoinColumn(name="product_id")
public Products getProduct() {
	return product;
}

public void setProduct(Products product) {
	this.product = product;
}


 
 
}
