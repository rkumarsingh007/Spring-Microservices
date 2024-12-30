package org.uncc.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.uncc.service.model.Order;

public interface OrderRepository extends JpaRepository<Order,Long> {

}
