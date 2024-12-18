package com.xworkz.commoun_module.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserDto {
       // private int id;
        private String name;
        private long phone;
        private long altPhone;
        private String email;
        private String alterEmail;
        private String location;

    }



