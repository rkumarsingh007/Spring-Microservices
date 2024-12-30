package org.uncc.products.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.uncc.products.model.Product;

import java.math.BigDecimal;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void save() {
        //Object creation
        Product product = Product.builder()
                .description("shit")
                .name("Android")
                .price(new BigDecimal(200))
                .id("asdg")
                .build();
        //Call the service
        Product saveProduct = productRepository.save(product);

        //Assert
        Assertions.assertThat(saveProduct).isNotNull();
        Assertions.assertThat(saveProduct.getId()).isEqualTo(product.getId());
    }
}