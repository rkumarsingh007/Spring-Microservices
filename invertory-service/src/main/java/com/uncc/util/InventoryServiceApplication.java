package com.uncc.util;

import com.uncc.util.dto.InventoryResponse;
import com.uncc.util.model.Inventory;
import com.uncc.util.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.beans.BeanProperty;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }


    @Bean
    public CommandLineRunner loadData(InventoryRepository inventoryRepository){
        return args -> {
            //ipone12&skuCode=android
            inventoryRepository.save(Inventory.builder().skuCode("iphone12").quantity(100).build());
            inventoryRepository.save(Inventory.builder().skuCode("android").quantity(0).build());
        };
    }
}
