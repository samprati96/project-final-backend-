package com.app.pojos;

import javax.persistence.*;

@Entity
public class Cart 
{
   private Integer cartId;
   private String username;
   private int productId;
   
   public Cart() 
   {      }

	public Cart(String username, int productId) {
		super();
		this.username = username;
		this.productId = productId;
	}
    
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	@Override
	public String toString() {
		return "Cart [username=" + username + ", productId=" + productId + "]";
	}
	
	
   
   

}
