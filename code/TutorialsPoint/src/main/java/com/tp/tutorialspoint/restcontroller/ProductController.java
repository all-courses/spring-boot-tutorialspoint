package com.tp.tutorialspoint.restcontroller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tp.tutorialspoint.TutorialsPointApplication;
import com.tp.tutorialspoint.model.Product;
import com.tp.tutorialspoint.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
   ProductService productService;

	@RequestMapping
	public ResponseEntity<Object> getProducts() {
		return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
	}

	public ResponseEntity<Object> getProduct(
			@RequestParam(value = "id", required = true) int pid,
			@RequestParam(value = "name", required = false, defaultValue = "honey") String name) {
		logger.info(String.format("Product Details: pid=%d and Name=%s",pid,name));
		return new ResponseEntity<>(productService.getProductDetails(pid), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> createProduct(@RequestBody Product product) {
		productService.createProduct(product);
		
		return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateProduct(@PathVariable("id") int id, @RequestBody Product product) {
		productService.updateProduct(id, product);
		
		return new ResponseEntity<>("Product is updated successsfully", HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@PathVariable("id") int id) {
		productService.deleteProduct(id);
		
		return new ResponseEntity<>("Product is deleted successsfully", HttpStatus.OK);
	}

	@RequestMapping(value = "/upload/{id}", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String fileUpload(@PathVariable("id") int id, @RequestParam("file") MultipartFile file) throws IOException {
		String UPLOAD_DIR_PATH = "./TutorialsPoint/Product_Images";
		File directory = new File(UPLOAD_DIR_PATH);
		if (! directory.exists()){
	        directory.mkdirs();
	    }
		
		File convertFile = new File(UPLOAD_DIR_PATH
				+ String.format("/Product_%d.%s", id, FilenameUtils.getExtension(file.getOriginalFilename())));
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
