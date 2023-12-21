package com.Book.Store.Project.service.implementation;

import com.Book.Store.Project.helpers.HashGenerator;
import com.Book.Store.Project.model.Users.SaltedUser;
import com.Book.Store.Project.model.Users.PlainUser;
import com.Book.Store.Project.repository.UsersRepository;
import com.Book.Store.Project.service.ProxyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class ProxyServiceImpl implements ProxyService {
    @Autowired
    UsersRepository usersRepository;

    @Override
    public Integer login(PlainUser user) throws NoSuchAlgorithmException {

        if(user.getUsername().equals(user.getPassword()) && user.getUsername().equals("Admin")){
            return -2;
        }
        SaltedUser u = (SaltedUser) usersRepository.findByUsername(user.getUsername());

        if(u == null){
            return -1;
        }
        String correctPassword = u.getPassword();
        String inputPassword = HashGenerator.toHexString(HashGenerator.getSHA(user.getPassword() + u.getSalt()));
        if (!correctPassword.equals(inputPassword)) {
            return -1;
        }
        return u.getId();
    }
}
