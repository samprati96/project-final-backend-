package com.app.dao;

import java.util.List;

import com.app.pojos.Products;
import com.app.pojos.Vendor;

public interface IVendorDao 
{
 String addProductToDb(int vendorId ,Products product);
 Vendor getVendor(int vendorId);
 List<Products> getProductsOfOneVendor(String username);
 String addNewVendor(Vendor vendor);
 Vendor validateVendor(String username);
 Products addProduct(Products p,int vendorId);
}
