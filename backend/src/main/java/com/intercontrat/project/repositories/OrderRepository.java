package com.intercontrat.project.repositories;

import com.intercontrat.project.entities.ClientOrder;
import com.intercontrat.project.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<ClientOrder, Long> {
    Optional<ClientOrder> findByStatus(OrderStatus status);
}
