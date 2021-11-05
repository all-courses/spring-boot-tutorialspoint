package com.tp.tutorialspoint.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.tp.tutorialspoint.exception.ProductNotfoundException;
import com.tp.tutorialspoint.model.Product;

@Service
public class ProductServiceImpl implements ProductService {

	private static Map<Integer, Product> productRepo = new HashMap<>();
	static {
		Product honey = new Product(1, "Honey");
		productRepo.put(honey.getId(), honey);

		Product almond = new Product(2, "Almond");
		productRepo.put(almond.getId(), almond);
	}

	@Override
	public void createProduct(Product product) {
		productRepo.put(product.getId(), product);
	}

	@Override
	public void updateProduct(int id, Product product) {
		if (!productRepo.containsKey(id))
			throw new ProductNotfoundException();
		
		productRepo.remove(id);
		product.setId(id);
		productRepo.put(id, product);
	}

	@Override
	public void deleteProduct(int id) {
		if (!productRepo.containsKey(id))
			throw new ProductNotfoundException();
		
		productRepo.remove(id);
	}

	@Override
	public Collection<Product> getProducts() {
		return productRepo.values();
	}

	@Override
	public Product getProductDetails(int pid) {
		return productRepo.get(pid);
	}

}
