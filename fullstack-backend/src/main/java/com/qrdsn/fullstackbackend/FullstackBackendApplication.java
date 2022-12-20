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
			//password == "Test"
			users.add(new User(1L, "Test@Example.com", "$2a$10$CchgbEpS22Puot3hhqHYMupD6z74tG7tihutyK02eCswUstCDvu1C", (byte)1));
			//password == "password"
			users.add(new User(2L, "quinnfontys@gmail.com", "$2a$10$Ota9Tzv0DrRBvGUaDAWlb.DSvX3i3KQDuUMcM04f6teq7PDcvlyZG", (byte)0));

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
			products.add(new Product(2L, categories.get(0), "Golden Delicious", "Delicious apple", "apple.png", (float)2.89,12, null ));
			products.add(new Product(3L, categories.get(0), "Pink Lady", "Despite its name, not pink nor a lady", "apple.png", (float)1.89,13, null ));
			products.add(new Product(4L, categories.get(0), "Fuji", "Many people think Fuji apples are named after Mount Fuji, but the name actually comes from Fujisaki, the Japanese town where they were developed. One large Fuji can give you 15 per cent of your daily vitamin C. Fujis are one of the best apples to freeze.", "apple.png", (float)2.56,1, null ));
			products.add(new Product(5L, categories.get(0), "Granny Smith", "The Granny Smith, also known as a green apple or sour apple, is an apple cultivar which originated in Australia in 1868.[1] It is named after Maria Ann Smith, who propagated the cultivar from a chance seedling. The tree is thought to be a hybrid of Malus sylvestris, the European wild apple, with the domesticated apple Malus domestica as the polleniser.[citation needed] The fruit is hard, firm and with a light green skin and crisp, juicy flesh. The flavour is tart and acidic. It remains firm when baked, making it a popular cooking apple[2] used in pies, where it can be sweetened. The apple goes from being completely green to turning yellow when overripe.[3] The US Apple Association reported in 2019 that the Granny Smith was the third most popular apple in the United States of America.[4]", "apple.png", (float)0.12,1231231, null ));
			products.add(new Product(6L, categories.get(0), "Honey Crisp", "another apple", "apple.png", (float)8.00,89, null ));
			products.add(new Product(7L, categories.get(1), "Cavendish", "this is a banana", "banana.jpg", (float)9.10,0, null ));
			products.add(new Product(8L, categories.get(1), "Gros Michel", "this is another banana", "banana.jpg", (float)2.12,100, null ));


			for (Product product : products) {
				productRespository.save(product);
			}

			//Seed carts
			List<Cart> carts = new ArrayList<>();
			carts.add(new Cart(1L, users.get(0), null));
			carts.add(new Cart(2L, users.get(1), null));

			for (Cart cart : carts) {
				cartRepository.save(cart);
			}

			//Seed cartProducts
			List<CartProduct> cartProducts = new ArrayList<>();
			cartProducts.add(new CartProduct(1L, carts.get(0), products.get(3), 2));
			cartProducts.add(new CartProduct(2L, carts.get(0), products.get(1), 3));
			cartProducts.add(new CartProduct(3L, carts.get(0), products.get(4), 1));
			cartProducts.add(new CartProduct(4L, carts.get(0), products.get(7), 2));
			cartProducts.add(new CartProduct(5L, carts.get(1), products.get(2), 1));
			cartProducts.add(new CartProduct(6L, carts.get(1), products.get(7), 3));

			for (CartProduct cartProduct : cartProducts) {
				cartProductRepository.save(cartProduct);
			}
		};
	}
}
