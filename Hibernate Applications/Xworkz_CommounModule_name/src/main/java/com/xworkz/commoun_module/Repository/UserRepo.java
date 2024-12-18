package com.xworkz.commoun_module.Repository;

import com.xworkz.commoun_module.entity.UserEntity;

public interface UserRepo {
    boolean save(UserEntity userEntity);
}
