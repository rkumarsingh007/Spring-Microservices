package org.uncc.products.model;

import jakarta.persistence.Entity;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Product {
    @jakarta.persistence.Id
    private String id;
    private String name;
    private String description;
    private BigDecimal price;

}
