package com.Book.Store.Project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemId implements Serializable {
    @Column(name = "cart_id")
    private int cartId;

    @Column(name = "book_id")
    private int bookId;

}
