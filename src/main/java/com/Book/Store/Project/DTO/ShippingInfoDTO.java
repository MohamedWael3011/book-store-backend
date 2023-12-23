package com.Book.Store.Project.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ShippingInfoDTO {

    String user_address;
    String user_city;
    String user_buildingno;
    String user_phone;
}
