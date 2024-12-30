package org.uncc.products.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.uncc.products.dto.ProductRequest;
import org.uncc.products.dto.ProductResponse;
import org.uncc.products.model.Product;
import org.uncc.products.repository.ProductRepository;

import java.security.cert.CollectionCertStoreParameters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {


    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
        productRepository.save(product);
        log.info("Product {} is saved", product.getName());

    }

    public List<ProductResponse> getAllProducts(){
        List<Product> products = productRepository.findAll();
        System.out.println(products);
        return products.stream().map(x->maptoProductResponse(x)).collect(Collectors.toList());
    }

    private ProductResponse maptoProductResponse(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}

