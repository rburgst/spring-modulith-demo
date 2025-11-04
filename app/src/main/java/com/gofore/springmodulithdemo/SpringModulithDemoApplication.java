package com.gofore.springmodulithdemo;

import com.gofore.springmodulithdemo.product.Product;
import com.gofore.springmodulithdemo.product.ProductService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringModulithDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringModulithDemoApplication.class, args);
    }

    @Bean
    public ApplicationRunner applicationRunner(ProductService productService) {
        return args -> {
//            productService.create(new Product("Sample Product", "This is a sample product", 100));
            productService.createSafe(new Product("Sample Product", "This is a safe product", 200));
        };
    }
}



