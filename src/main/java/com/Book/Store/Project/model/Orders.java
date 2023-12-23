package com.Book.Store.Project.model;


import com.Book.Store.Project.model.Users.PlainUser;
import com.Book.Store.Project.model.Users.SaltedUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Orders {
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date order_date;
    @Column(name = "order_status")
    private String orderStatus;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    SaltedUser user;
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Payment payment;

    String user_address;
    String user_city;
    String user_buildingno;
    String user_phone;

}
