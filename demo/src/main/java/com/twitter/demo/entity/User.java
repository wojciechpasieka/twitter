package com.twitter.demo.entity;


import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;
    private String password;
    private String role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_details_id")
    private UserDetails userDetailsId;

    @Column(name = "lock_date")
    private Timestamp lockDate;

    @Column(name = "unlock_date")
    private Timestamp unlockDate;

    private Timestamp lock_date;

    @Column(name = "unlock_date")
    private Timestamp unLock_date;

}
