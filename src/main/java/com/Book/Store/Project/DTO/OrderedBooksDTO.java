package com.Book.Store.Project.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class OrderedBooksDTO {

    private int book_id;
    private int quantity;

}
