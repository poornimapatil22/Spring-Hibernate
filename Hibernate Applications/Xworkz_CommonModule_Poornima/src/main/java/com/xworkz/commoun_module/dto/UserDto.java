package com.xworkz.commoun_module.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.*;

@Data
@RequiredArgsConstructor
public class UserDto {

    @NotEmpty(message = "User name cannot be empty.")
    @Size(min = 3, max = 50, message = "User name should be between 3 and 50 characters.")
    private String name;

    @Digits(integer = 10, fraction = 0)
    private long phone;

    @Digits(integer = 10, fraction = 0)
    private long altPhone;

    @Email(message = "Email should be correct")
    private String email;

    @Email(message = "Alternative Email should be correct")
    private String altEmail;

    @NotEmpty(message = "Location name cannot be empty.")
    @Size(min = 3, max = 50, message = "Location name should be between 3 and 50 characters.")
    private String location;

    private Integer count ;

    }



