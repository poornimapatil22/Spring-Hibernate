package com.xworkz.commoun_module.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.xworkz.commoun_module.constants.LocationConstants;
import com.xworkz.commoun_module.repository.UserRepo;
import com.xworkz.commoun_module.service.UserService;
import com.xworkz.commoun_module.dto.UserDto;
import com.xworkz.commoun_module.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RequestMapping("/")
@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserRepo userRepo;
    UserController(){
        System.out.println("no arg const of controller");
    }

    @PostMapping("/signUp")
    public String signUp(Model model, @Valid UserDto userDto, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            boolean isSaved = userService.validAndSave(userDto);
            if(isSaved){
                model.addAttribute("message", "SignUp Successful!");
            return "SignIn";
            }
        }
        model.addAttribute("error", bindingResult.getAllErrors());
        model.addAttribute("user", userDto);
        return "SignUp";
        }


    @GetMapping(value = "/signUpAgain")
        public String signUpAgain(Model model){
            List<LocationConstants> locationList=new ArrayList<>(Arrays.asList(LocationConstants.values()));
            locationList.forEach(n-> System.out.println(n));
            model.addAttribute("locationListSend",locationList);
        return "SignUp";
        }

    @GetMapping(value = "/updateAgain")
    public String updateAgain(Model model){
        List<LocationConstants> locationList=new ArrayList<>(Arrays.asList(LocationConstants.values()));
        locationList.forEach(n-> System.out.println(n));
        model.addAttribute("locationListSend",locationList);
        return "UpdateProfile";
    }



    @PostMapping(value = "/updateProfile")
    public String updateProfile(
            @RequestParam String email,
            @RequestParam String name,
            @RequestParam String location,
            @RequestParam Long altPhone,
            @RequestParam Long phone,
            @RequestParam String altEmail,
            Model model) {
        UserEntity updatedUser = userService.updateUserEntity(email, name, location, altPhone, phone, altEmail);
        System.out.println(updatedUser);
        if (updatedUser != null) {
            return "UpdatedSuccess";
        }
        model.addAttribute("errorIs", "Profile update failed");
        return "UpdateProfile";
    }



    @PostMapping(value = "/signIn")
    public String signIn(@RequestParam String email, @RequestParam String password, Model model) {
        UserEntity userEntity = userService.getUserByEmail(email);
        if (userEntity == null) {
            return "SignUp";
        }
        if (userEntity.getCount() == -1) {
            return "ResetPassword";
        }
        if (userEntity.getFailedAttempts() >= 4) {
            model.addAttribute("lock", "Your account is locked due to too many failed login attempts. Please try again later.");
            return "SignUp";
        }

        String storedPassword = userEntity.getPassword();
        if (!storedPassword.equals(password)){
            userEntity.setFailedAttempts(userEntity.getFailedAttempts() + 1);
            // If failed attempts reach 4, lock the account
            if (userEntity.getFailedAttempts() >= 4){
                userEntity.setLocked(true);
                userRepo.save(userEntity);
                model.addAttribute("say","Your Account has locked");
                return "SignUp";
            }
            else {
                userRepo.save(userEntity);
                int remainingAttempts = 3 - userEntity.getFailedAttempts();
                model.addAttribute("error", "Incorrect password. You have " + remainingAttempts + " attempts remaining.");
                return "SignIn";
            }
            }
        else {
            userEntity.setFailedAttempts(0);
            userRepo.save(userEntity);
        }
            //model.addAttribute("name", userEntity.getName());
            return "Success";
    }


    @PostMapping("/resetPassword")
    public String resetPassword(@RequestParam String email, @RequestParam String newPassword,Model model,String oldPassword){
        UserEntity userEntity = userService.getUserByEmail(email);

        if (userEntity != null){
            if (userEntity.getCount() == -1) {
                if(userEntity.getPassword().equals(oldPassword)) {
                    userEntity.setPassword(String.valueOf(newPassword));
                    userEntity.setCount(1);
                    userRepo.save(userEntity);
                    return "Success";
                }
                else{
                    return "ResetPassword";

                }
            } else {

                return "SignIn";
            }
        }else{

            return "SignUp";
        }
    }
}
