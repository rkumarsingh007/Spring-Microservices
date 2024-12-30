package org.uncc.products.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.uncc.products.dto.ProductRequest;
import org.uncc.products.model.Product;
import org.uncc.products.repository.ProductRepository;
import java.math.BigDecimal;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void createProduct() {
        ProductRequest productRequest = ProductRequest.builder()
                .description("shit")
                .name("Android")
                .price(new BigDecimal(200))
                .build();
        productService.createProduct(productRequest);
        Product savedProd = Product.builder().name(productRequest.getName()).price(productRequest.getPrice())
                        .description(productRequest.getDescription()).build();
        verify(productRepository).save(savedProd);
    }

    @Test
    void canGetAllProducts() {
        //when
        productService.getAllProducts();
        //then
        verify(productRepository).findAll();
    }
}