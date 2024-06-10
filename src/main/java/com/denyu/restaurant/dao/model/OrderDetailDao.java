package com.denyu.restaurant.dao.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "order_detail")
public class OrderDetailDao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "order_detail_id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    private OrderDao order;

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private ProductDao product;

    @Column(name = "order_detail_qty")
    private int qty;

    @Column(name = "order_detail_notes")
    private String notes;
}
