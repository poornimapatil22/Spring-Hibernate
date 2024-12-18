package com.xworkz.commoun_module.Service;

import com.xworkz.commoun_module.dto.UserDto;
import com.xworkz.commoun_module.entity.UserEntity;

public interface UserService {
    boolean validAndSave(UserDto userDto);
}
