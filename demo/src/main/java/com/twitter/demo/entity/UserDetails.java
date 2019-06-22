package com.twitter.demo.entity;


import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "user_details")
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surename;
    private String email;

    @Column(name = "born_date")
    private Timestamp bornDate;

    @Column(name = "join_date")
    private Timestamp joinDate;
}
