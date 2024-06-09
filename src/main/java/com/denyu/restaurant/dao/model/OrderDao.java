package com.denyu.restaurant.dao.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@Table(name = "\"order\"")
public class OrderDao {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "order_id")
    private String id;

    @Column(name = "order_table_number")
    private int table;

    @Column(name = "order_dt")
    private Timestamp dateTime;

    @Column(name = "order_status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserDao userDao;



}
