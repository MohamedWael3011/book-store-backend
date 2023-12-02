package com.Book.Store.Project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Cloneable {
    private static int bookIds = 0;

    private int bookID;
    private String title;
    private String author;
    private String category;
    private double price;
    private int quantity;
    private String image_url;


    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }
}
