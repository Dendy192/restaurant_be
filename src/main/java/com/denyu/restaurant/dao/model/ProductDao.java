package com.denyu.restaurant.dao.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "product")
public class ProductDao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "product_id")
    private UUID id;

    @Column(name = "product_name")
    private String name;

    @Column(name = "product_photo_url")
    private String photo_url;

    @Column(name = "product_price")
    private String price;

    @Column(name = "product_status")
    private String status;

}