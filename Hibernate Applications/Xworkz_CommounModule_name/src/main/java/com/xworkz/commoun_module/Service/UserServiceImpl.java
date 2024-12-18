package com.xworkz.commoun_module.Service;

import com.xworkz.commoun_module.Repository.UserRepo;
import com.xworkz.commoun_module.dto.UserDto;
import com.xworkz.commoun_module.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepo userRepo;

    @Override
    public boolean validAndSave(UserDto userDto) {
        int password=0;
        if(userDto.getEmail()!= null){
            password= randomNumGenerator();
        }
        UserEntity userEntity=new UserEntity();
        userEntity.setName(userDto.getName());
        userEntity.setAlterEmail(userDto.getAlterEmail());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPassword(password);
        userEntity.setPhone(userDto.getPhone());
        userEntity.setAltPhone(userDto.getAltPhone());
        userEntity.setLocation(userDto.getLocation());
        this.userRepo.save(userEntity);
        return true;
    }

   private static Random random=new Random();
    public static int randomNumGenerator(){
        return random.nextInt(999999) ;
    }
}
