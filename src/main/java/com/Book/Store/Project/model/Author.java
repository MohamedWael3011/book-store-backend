package com.Book.Store.Project.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Author {
    @Override
    public String toString() {
        return "Author{" +
                "author_id=" + author_id +
                ", name='" + name + '\'' +
                ", books=" + books +
                '}';
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int author_id;
    @Column(name = "author_name")
    String name;
    @OneToMany(mappedBy = "author")
    @JsonIgnore
    Set<Books> books;
}
