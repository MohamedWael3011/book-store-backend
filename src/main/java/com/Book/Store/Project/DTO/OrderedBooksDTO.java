package com.Book.Store.Project.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class OrderedBooksDTO {
    @Override
    public String toString() {
        return "OrderedBooksDTO{" +
                "book_id=" + book_id +
                ", quantity=" + quantity +
                '}';
    }

    private int book_id;
    private int quantity;

}
