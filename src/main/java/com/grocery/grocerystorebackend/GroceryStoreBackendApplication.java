package com.grocery.grocerystorebackend;

import com.grocery.grocerystorebackend.entity.Cart;
import com.grocery.grocerystorebackend.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class GroceryStoreBackendApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(GroceryStoreBackendApplication.class, args);
	}


	@Autowired
	private CartRepository cartRepository;
	@Override
	public void run(String... args) throws Exception {

	}
}
