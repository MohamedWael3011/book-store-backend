package com.Book.Store.Project.model;

import com.Book.Store.Project.model.Author;
import com.Book.Store.Project.model.Review;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Books  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int id;
    private String book_name;
    private String description;
    private int num_sales;
    private int rates;
    private double price;
    private int quantity_in_stock;
    private String image_url;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "genre_id")
    Genre genre;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<CartItem> cartItems;
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "author_id")
    Author author;

    @OneToMany(mappedBy = "book")
    List<Review> reviews;

}
