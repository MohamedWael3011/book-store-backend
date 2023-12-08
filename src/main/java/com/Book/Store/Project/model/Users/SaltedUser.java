package com.Book.Store.Project.model.Users;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "users")
@PrimaryKeyJoinColumn(name = "id")
public class SaltedUser extends PlainUser {

    private String salt;

    public SaltedUser(PlainUser decoratedUser, String salt) {
        super(decoratedUser.getId(), decoratedUser.getUsername(), decoratedUser.getPassword(),
                decoratedUser.getPhone(), decoratedUser.getAddress(), decoratedUser.getReviews());
        this.salt = salt;
    }
}
