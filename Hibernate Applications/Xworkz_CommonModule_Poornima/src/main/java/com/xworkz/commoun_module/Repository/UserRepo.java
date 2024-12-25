package com.xworkz.commoun_module.Repository;

import com.xworkz.commoun_module.entity.UserEntity;

public interface UserRepo {
    boolean save(UserEntity userEntity);
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
