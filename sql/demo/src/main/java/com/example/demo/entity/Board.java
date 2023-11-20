package com.example.demo.entity;

import javax.persistence.*;

@Entity
public class Board {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column (length = 100, nullable = true)
    private String email;

    @Column (length = 256, nullable = true)
    private String password;

    @Column (length = 45, nullable = true)
    private String user_name;

    @Column (length = 100, nullable = false)
    private String provider;

    @Column (length = 11, nullable = true)
    private String phone_number;

    @Column (length = 30, nullable = true)
    private String roles;

    @Column (length = 3, nullable = true)
    private int user_age;
}
