package com.tp.tutorialspoint.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import org.apache.commons.io.FilenameUtils;

import com.tp.tutorialspoint.exception.ProductNotfoundException;
import com.tp.tutorialspoint.model.Product;

@RestController
@RequestMapping("/products")
public class ProductController {
	private static Map<Integer, Product> productRepo = new HashMap<>();
	static {
		Product honey = new Product(1, "Honey");
		productRepo.put(honey.getId(), honey);

		Product almond = new Product(2, "Almond");
		productRepo.put(almond.getId(), almond);
	}

	@RequestMapping
	public ResponseEntity<Object> getProducts() {
		return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
	}

	public ResponseEntity<Object> getProduct(
			@RequestParam(value = "name", required = false, defaultValue = "honey") String name) {
		return null;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> createProduct(@RequestBody Product product) {
		productRepo.put(product.getId(), product);
		return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateProduct(@PathVariable("id") int id, @RequestBody Product product) {
		if (!productRepo.containsKey(id))
			throw new ProductNotfoundException();

		productRepo.remove(id);
		product.setId(id);
		productRepo.put(id, product);
		return new ResponseEntity<>("Product is updated successsfully", HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@PathVariable("id") int id) {
		if (!productRepo.containsKey(id))
			throw new ProductNotfoundException();

		productRepo.remove(id);
		return new ResponseEntity<>("Product is deleted successsfully", HttpStatus.OK);
	}

	@RequestMapping(value = "/upload/{id}", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String fileUpload(@PathVariable("id") int id, @RequestParam("file") MultipartFile file) throws IOException {
		File convertFile = new File("./TutorialsPoint/Product_Images/"
				+ String.format("Product_%d.%s", id, FilenameUtils.getExtension(file.getOriginalFilename())));
		convertFile.createNewFile();
		FileOutputStream fout = new FileOutputStream(convertFile);
		fout.write(file.getBytes());
		fout.close();
		return "File is upload successfully";
	}

	@RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> downloadFile(@PathVariable("id") int id) throws IOException {
		String filename = String.format("./TutorialsPoint/Product_Images/Product_%d.jpg", id);
		File file = new File(filename);
		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
		HttpHeaders headers = new HttpHeaders();

		headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");

		ResponseEntity<Object> responseEntity = ResponseEntity.ok()
				.headers(headers)
				.contentLength(file.length())
				.contentType(MediaType.APPLICATION_OCTET_STREAM)
//				.contentType(MediaType.parseMediaType("image/jpeg"))
				.body(resource);

		return responseEntity;
	}

}
