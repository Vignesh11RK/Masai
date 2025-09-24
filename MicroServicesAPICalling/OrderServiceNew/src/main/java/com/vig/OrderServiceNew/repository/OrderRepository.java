package com.vig.OrderServiceNew.repository;

import com.vig.OrderServiceNew.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {}
