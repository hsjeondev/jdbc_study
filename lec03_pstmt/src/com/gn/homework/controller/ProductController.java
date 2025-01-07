package com.gn.homework.controller;

import com.gn.homework.model.dao.ProductDao;

public class ProductController {
	private ProductDao pd = new ProductDao();
	public boolean checkProduct(String productName) {
		return pd.checkProduct(productName);
	}
	
	public boolean checkProduct(int productId) {
		
		return false;
	}
}
