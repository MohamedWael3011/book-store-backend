package com.Book.Store.Project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Table
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(Order_Book.CompositeKey.class)
public class Order_Book {

    @Id
    @Column(name = "order_id")
    private int order_id;

    @Id
    @Column(name = "book_id")
    private int book_id;

    private int quantity;

    public static class CompositeKey implements Serializable {
        private int order_id;
        private int book_id;

        public int getOrderId() {
            return order_id;
        }

        public void setOrderId(int order_id) {
            this.order_id = order_id;
        }

        public int getBookId() {
            return book_id;
        }

        public void setBookId(int book_id) {
            this.book_id = book_id;
        }

        public CompositeKey(final int order_id, final int book_id) {
            this.order_id = order_id;
            this.book_id = book_id;
        }

        public CompositeKey() {
        }
    }
}
