package com.javatechie.os.api.repository;

import com.javatechie.os.api.entitiy.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
