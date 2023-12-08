package com.Book.Store.Project.DTO;


import com.Book.Store.Project.model.Users.PlainUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class UserDTO {
    int id;
    String username;
    int phone;
    String address;

    public UserDTO(PlainUser user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.phone = user.getPhone();
        this.address = user.getAddress();
    }

}
