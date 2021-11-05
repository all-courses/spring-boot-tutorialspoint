package com.tp.tutorialspoint.service;

import java.util.Collection;

import com.tp.tutorialspoint.model.Product;

public interface ProductService {
	public abstract void createProduct(Product product);

	public abstract void updateProduct(int id, Product product);

	public abstract void deleteProduct(int id);

	public abstract Collection<Product> getProducts();

	public abstract Product getProductDetails(int pid);
}
