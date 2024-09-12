package com.intercontrat.project.entities;

import com.intercontrat.project.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ClientOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private Date orderDate;

    private Date deliveryDate;

    private String address;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "client_orders_products",
            joinColumns = @JoinColumn(name = "client_order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    @ToString.Exclude
    private List<Product> products = new ArrayList<>();
}
