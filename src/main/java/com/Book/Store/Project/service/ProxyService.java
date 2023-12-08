package com.Book.Store.Project.service;

import com.Book.Store.Project.model.Users.PlainUser;

import java.security.NoSuchAlgorithmException;

public interface ProxyService {
    Integer login(PlainUser user) throws NoSuchAlgorithmException;
}
