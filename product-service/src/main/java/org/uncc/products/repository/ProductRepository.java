package org.uncc.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.uncc.products.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    Product save(Product product);
}
