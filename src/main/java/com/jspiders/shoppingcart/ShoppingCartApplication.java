package com.jspiders.shoppingcart;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class ShoppingCartApplication {

	@SuppressWarnings("rawtypes")
	List<VendorExtension> vendorExtensions = new ArrayList<VendorExtension>();
	Contact contact = new Contact("Jspiders", "https://jspiders.com/", "enquiry@jspiders.com");

	ApiInfo apiInfo = new ApiInfo("Shopping Cart",
			"The application design contains three modules one is for the customers who wish to buy the products. And another is for the merchants who maintain and updates the information regarding the product. And third is for admin who can manage both customer and merchant.\n"
					+ "The end-user to use this product are the common people for whom the application is to be hosted on the web and the admin maintains the database.\n"
					+ "The application that is deployed on the customer’s database like RDBMS, the information regarding the items is highlighted and forwarded from the database for the customer (front view) based on the choice through the menu list and based on all these searches and transactions the database of all the products is updated at the end of each transaction.\n"
					+ "",
			"snapshoot-0.0.01", "https://jspiders.com", contact, "www.jspiders.com", "terms and conditions",
			vendorExtensions);

	@Bean
	public Docket myDocket() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.jspiders.shoppingcart")).build().apiInfo(apiInfo);

	}

	public static void main(String[] args) {
		SpringApplication.run(ShoppingCartApplication.class, args);
	}
}
