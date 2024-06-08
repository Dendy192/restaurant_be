package com.denyu.restaurant.dao.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "rs_user")
public class UserDao {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private String id;

    @Column(name = "user_name")
    private String name;

    @Column(name = "user_password")
    private String password;

    @Column(name = "user_role")
    private String role;

}
