package com.twitter.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "create_date")
    private Timestamp createDate;

    private String text;

    @Column(name = "modify_date")
    private Timestamp modifyDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User userId;

    @Column(name = "delete_date")
    private Timestamp deleteDate;
}
