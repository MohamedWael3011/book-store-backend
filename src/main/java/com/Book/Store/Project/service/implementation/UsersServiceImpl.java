package com.Book.Store.Project.service.implementation;

import com.Book.Store.Project.DTO.UserDTO;
import com.Book.Store.Project.helpers.HashGenerator;
import com.Book.Store.Project.model.Users.SaltedUser;
import com.Book.Store.Project.model.Users.PlainUser;
import com.Book.Store.Project.repository.UsersRepository;
import com.Book.Store.Project.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

import static com.Book.Store.Project.helpers.RandomStringGenerator.generateRandomString;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDTO addUser(PlainUser user) throws NoSuchAlgorithmException {
        try {
            // Check if the username already exists
            if (usersRepository.findByUsername(user.getUsername()) != null) {
                return null;
            }

            String salt = generateRandomString(20);
            SaltedUser saltedUser = new SaltedUser(user, salt);
            saltedUser.setPassword(HashGenerator.toHexString(HashGenerator.getSHA(saltedUser.getPassword() + salt)));
            SaltedUser savedUser = usersRepository.save(saltedUser);

            return new UserDTO(savedUser.getId(),savedUser.getUsername(),savedUser.getPhone(),savedUser.getAddress());
        } catch (Exception e) {
            throw new RuntimeException("Error adding user", e);
        }
    }

    @Override
    public void delUser(int user_id) {
        PlainUser user = usersRepository.findById(user_id).orElseThrow(() -> {
            return new RuntimeException("There is no Book with this ID");
        });
        usersRepository.deleteById(user_id);
    }

    @Override
    public List<UserDTO> getUsers() {
        return usersRepository.findAll().stream().map(u -> new UserDTO(u)).collect(Collectors.toList());
    }

    @Override
    public UserDTO getUser(int user_id) {
        PlainUser user = usersRepository.findById(user_id).orElseThrow(() -> {
            return new RuntimeException("There is no User with this ID");
        });
        return  new UserDTO(user);
    }


}
