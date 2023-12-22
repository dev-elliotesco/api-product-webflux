package com.reactive.router;

import com.reactive.handler.ProductHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class ProductRouter {

    private static final String PRODUCT_BASE_URL = "/product";

    RouterFunction<ServerResponse> router (ProductHandler productHandler) {
        return RouterFunctions.route()
                .GET(PRODUCT_BASE_URL, productHandler::getAll)
                .GET(PRODUCT_BASE_URL + "/{id}", productHandler::getOne)
                .POST(PRODUCT_BASE_URL, productHandler::save)
                .PUT(PRODUCT_BASE_URL + "/{id}", productHandler::update)
                .DELETE(PRODUCT_BASE_URL + "/{id}", productHandler::delete)
                .build();
    }
}
