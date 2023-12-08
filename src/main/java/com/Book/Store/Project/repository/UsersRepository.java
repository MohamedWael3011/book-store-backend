package com.Book.Store.Project.repository;

import com.Book.Store.Project.model.Users.SaltedUser;
import com.Book.Store.Project.model.Users.PlainUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<SaltedUser,Integer> {
    PlainUser findByUsername(String username);

}
