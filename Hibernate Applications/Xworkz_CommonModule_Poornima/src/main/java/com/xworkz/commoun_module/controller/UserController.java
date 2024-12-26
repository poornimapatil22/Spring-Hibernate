package com.xworkz.commoun_module.controller;

import com.xworkz.commoun_module.repository.UserRepo;
import com.xworkz.commoun_module.service.UserService;
import com.xworkz.commoun_module.dto.UserDto;
import com.xworkz.commoun_module.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;


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
            return "SignIn.jsp";
        }
        }


        model.addAttribute("error", bindingResult.getAllErrors());
        model.addAttribute("user", userDto);
            return "SignUp.jsp";
        }





    @PostMapping(value = "/signIn")
    public String signIn(@RequestParam String email, @RequestParam String password, Model model){
        String name = userService.getNameByEmailAndPassword(email, password);
        UserEntity userEntity = userService.getUserByEmail(email);
        System.out.println("Retrieved Name: " + name);
        if (userEntity.getCount() == -1) {

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

                return "SignIn.jsp";
            }
        }else{

            return "SignUp.jsp";
        }
    }
}
