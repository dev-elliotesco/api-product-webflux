package com.reactive.service;

import com.reactive.entity.Product;
import com.reactive.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Flux<Product> getAll() {
        return productRepository.findAll();
    }

    public Mono<Product> getById(Integer id) {
        return productRepository.findById(id);
    }

    public Mono<Product> save(Product product) {
        return productRepository.save(product);
    }

    public Mono<Product> update(Integer id, Product product) {
        return productRepository.save(new Product(id, product.getName(), product.getPrice()));
    }

    public Mono<Void> delete(Integer id) {
        return productRepository.deleteById(id);
    }
}
