package com.xworkz.commoun_module.controller;

import com.xworkz.commoun_module.Service.UserService;
import com.xworkz.commoun_module.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@RequestMapping("/")
@Controller
public class UserController {
    @Autowired
    UserService userService;
    UserController(){
        System.out.println("no arg const of controller");
    }

    @PostMapping("/signUp")
    public String  signUp(Model model, UserDto userDto){
        boolean check=userService.validAndSave(userDto);
        if(check) {
            model.addAttribute("SuccessMsg", "SignUp Successful!");
        }

        return  "SignUp.jsp";
    }
}
