package com.qrdsn.fullstackbackend;

import com.qrdsn.fullstackbackend.model.*;
import com.qrdsn.fullstackbackend.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class FullstackBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FullstackBackendApplication.class, args);
	}

	@Bean
	CommandLineRunner run(CartProductRepository cartProductRepository, CartRepository cartRepository, CategoryRepository categoryRepository, ProductRespository productRespository, UserRepository userRepository) {
		return args -> {
			//Seed users
			List<User> users = new ArrayList<>();
			users.add(new User(1L, "Test@Example.com", "Test", (byte)1));
//			users.add(new User(1L, "quinnfontys@gmail.com", "password", (byte)0));

			for (User user : users) {
				userRepository.save(user);
			}

			//Seed category
			List<Category> categories = new ArrayList<>();
			categories.add(new Category(1L, null, "Apples", "This is the first item that came to mind, and suddenly the only things I'm filing this database with is fruit for some reason"));
			categories.add(new Category(2L, null, "Bananas", "These are yellow-ish fruits that obtain their curved shape due to their immense desire for sunlight"));

			for (Category category : categories) {
				categoryRepository.save(category);
			}

			//Seed products
			List<Product> products = new ArrayList<>();
			products.add(new Product(1L, categories.get(0), "Gala", "An apple :D", "gala.jpg", (float)2.99,99, null ));

			for (Product product : products) {
				productRespository.save(product);
			}

			//Seed carts
			List<Cart> carts = new ArrayList<>();
			carts.add(new Cart(1L, users.get(0), null));

			for (Cart cart : carts) {
				cartRepository.save(cart);
			}

			//Seed cartProducts
			List<CartProduct> cartProducts = new ArrayList<>();
			cartProducts.add(new CartProduct(1L, carts.get(0), products.get(0), 2));

			for (CartProduct cartProduct : cartProducts) {
				cartProductRepository.save(cartProduct);
			}
		};
	}
}
