package com.gofore.springmodulithdemo;

import com.gofore.springmodulithdemo.product.Product;
import com.gofore.springmodulithdemo.product.ProductService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringModulithDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringModulithDemoApplication.class, args)
                .getBean(ProductService.class)
                .create(new Product("Sample Product", "This is a sample product", 100));
    }

}
