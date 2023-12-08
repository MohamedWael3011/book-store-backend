package com.Book.Store.Project.service;

import com.Book.Store.Project.DTO.UserDTO;
import com.Book.Store.Project.model.Users.PlainUser;
import com.Book.Store.Project.model.Users.SaltedUser;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface UsersService {

    PlainUser addUser(PlainUser user) throws NoSuchAlgorithmException;
    void delUser(int user_id);
    List<UserDTO> getUsers();

    UserDTO getUser(int user_id);

}
