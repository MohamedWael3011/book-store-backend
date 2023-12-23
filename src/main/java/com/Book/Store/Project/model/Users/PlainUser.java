package com.Book.Store.Project.model.Users;


import com.Book.Store.Project.model.Cart;
import com.Book.Store.Project.model.Orders;
import com.Book.Store.Project.model.Review;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class PlainUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    int id;

    String username;

    String password;

    int phone;

    String address;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    List<Review> reviews;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    List<Orders> orders;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private Cart cart;


}
