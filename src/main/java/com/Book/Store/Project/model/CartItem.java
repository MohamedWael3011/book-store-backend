package com.Book.Store.Project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "Cart_Item")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {

    @EmbeddedId
    private CartItemId id;

    @ManyToOne
    @MapsId("cartId")
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name = "book_id")
    private Books book;

    @Column(name = "quantity")
    private Integer quantity;

}

