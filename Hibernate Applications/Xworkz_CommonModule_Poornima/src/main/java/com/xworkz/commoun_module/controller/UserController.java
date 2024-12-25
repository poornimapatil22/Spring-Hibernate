package com.xworkz.commoun_module.controller;

import com.xworkz.commoun_module.Repository.UserRepo;
import com.xworkz.commoun_module.Service.UserService;
import com.xworkz.commoun_module.dto.UserDto;
import com.xworkz.commoun_module.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


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

    @PostMapping(value = "/signUp")
    public String  signUp(Model model, UserDto userDto){
        boolean check=userService.validAndSave(userDto);
        if(check) {
            model.addAttribute("SuccessMsg", "SignUp Successful!");
        }
        return  "SignIn.jsp";
    }

    @PostMapping(value = "/signIn")
    public String signIn(@RequestParam String email, @RequestParam String password, Model model){
        String name = userService.getNameByEmailAndPassword(email, password);
        UserEntity userEntity = userService.getUserByEmail(email);
        System.out.println("Retrieved Name: " + name);
        if (userEntity.getCount() == -1) {
            model.addAttribute("message","user has to reset the password");
            return "ResetPassword.jsp";

        }
        return "Success.jsp";
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
                    return "Success.jsp";
                }
                else{
                    return "ResetPassword.jsp";

                }
            } else {
                model.addAttribute("message", "User does not need a reset.");
                return "SignIn.jsp";
            }
        }else{
            model.addAttribute("message", "User not found.");
            return "SignUp.jsp";
        }
    }
}
