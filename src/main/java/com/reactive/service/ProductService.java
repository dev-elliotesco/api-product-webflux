package com.reactive.service;

import com.reactive.entity.Product;
import com.reactive.exception.CustomException;
import com.reactive.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductService {

    private  final static String PRODUCT_NOT_FOUND = "Product not found";

    private final ProductRepository productRepository;

    public Flux<Product> getAll() {
        return productRepository.findAll();
    }

    public Mono<Product> getById(int id) {
        return productRepository.findById(id)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, PRODUCT_NOT_FOUND)));
    }

    public Mono<Product> save(Product product) {
        return productRepository.save(product);
    }

    public Mono<Product> update(Integer id, Product product) {
        Mono<Boolean> productExists = productRepository.findById(id).hasElement();
        return productExists.flatMap(
                existsId -> existsId ?
                        productRepository.save( new Product(id, product.getName(), product.getPrice()))
                        : Mono.error(new CustomException(HttpStatus.BAD_REQUEST,PRODUCT_NOT_FOUND)));
    }

    public Mono<Void> delete(Integer id) {
        Mono<Boolean> productExists = productRepository.findById(id).hasElement();
        return productExists.flatMap(
                existsId -> existsId ?
                        productRepository.deleteById(id)
                        : Mono.error(new CustomException(HttpStatus.NOT_FOUND,PRODUCT_NOT_FOUND)));
    }
}
