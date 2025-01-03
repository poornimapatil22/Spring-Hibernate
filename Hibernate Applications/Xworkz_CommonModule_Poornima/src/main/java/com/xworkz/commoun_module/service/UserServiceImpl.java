package com.xworkz.commoun_module.service;

import com.xworkz.commoun_module.entity.AbstractAuditEntity;
import com.xworkz.commoun_module.repository.UserRepo;
import com.xworkz.commoun_module.dto.UserDto;
import com.xworkz.commoun_module.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepo userRepo;

   // AbstractAuditEntity auditEntity=new AbstractAuditEntity();

    @Override
    public boolean validAndSave(UserDto userDto) {
        String password="";
        if(userDto.getEmail()!= null){
            password= randomNumGenerator();
        }

        UserEntity userEntity=new UserEntity();
        userEntity.setName(userDto.getName());
        userEntity.setAltEmail(userDto.getAltEmail());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPassword(password);
        userEntity.setPhone(userDto.getPhone());
        userEntity.setAltPhone(userDto.getAltPhone());
        userEntity.setLocation(userDto.getLocation());
        userEntity.setCount(-1);
        userEntity.setFailedAttempts(0);
        userEntity.setLocked(false);
        userEntity.setCreatedBy(userDto.getName());
        userEntity.setUpdateBy(userDto.getName());
        userEntity.getCreatedOn();
        userEntity.getUpdatedOn();
        this.userRepo.save(userEntity);
        this.userRepo.saveEmail(userDto.getEmail(),password);

        return true;
    }

    @Override
    public Long countByLocation(String location) {
        Long countByLocation= userRepo.countByLocation(location);
        return countByLocation;
    }

    @Override
    public Long countByAltPhone(long altPhone) {
        Long countByAltPhone= userRepo.countByAltPhone(altPhone);
        return countByAltPhone;
    }

    @Override
    public Long countByPhone(long phone) {
        Long countByPhone=userRepo.countByPhone(phone);
        return countByPhone;
    }
    @Override
    public Long countName(String name) {
        Long count= userRepo.countName(name);
        return count;
    }

    @Override
    public Long countAltEmail(String altEmail) {
        Long count= userRepo.countAltEmail(altEmail);
        return count;
    }

    @Override
    public String getNameByEmailAndPassword(String email, String  password) {
        String name = userRepo.getNameByEmailAndPassword(email, password);
        return name;
    }

    @Override
    public Long countByEmail(String email) {
        return userRepo.countByEmail(email);
    }

    @Override
    public UserEntity getUserByEmail(String email) {
        return userRepo.getUserByEmail(email);
    }

    @Override
    public boolean saveEmail(String email, String password) {
        return userRepo.saveEmail(email, password);

    }

    @Override
    public UserEntity updateUserEntity(String email,String name, String location, Long altPhone, Long phone, String altEmail) {
        return userRepo.updateUserEntity(email, name, location, altPhone, phone, altEmail);
    }


    private static Random random=new Random();
    public static String randomNumGenerator(){
       int i= random.nextInt(999999);
        return String.valueOf(i)  ;
    }



}
