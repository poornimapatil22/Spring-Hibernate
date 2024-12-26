package com.xworkz.commoun_module.service;

import com.xworkz.commoun_module.dto.UserDto;
import com.xworkz.commoun_module.entity.UserEntity;

public interface UserService {
    boolean validAndSave(UserDto userDto);
    Long countByLocation(String location);
    Long countByAltPhone(long altPhone);
    Long countByPhone(long phone);
    Long countAltEmail(String altEmail);
    String getNameByEmailAndPassword(String email, String password);
    Long countName(String name);
    Long countByEmail(String email);
    UserEntity getUserByEmail(String email);
    public boolean saveEmail(String email, String password);
}
