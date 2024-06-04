package com.lcwd.order.repository;

import com.lcwd.order.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,String>
{
    //custom finder methods
    List<Order> findByUserId(String userId);
    List<Order> findByProductId(String productId);

}
