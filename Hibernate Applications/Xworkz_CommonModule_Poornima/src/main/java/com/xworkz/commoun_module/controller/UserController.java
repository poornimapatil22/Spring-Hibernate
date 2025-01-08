package com.xworkz.commoun_module.controller;


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
import org.springframework.web.multipart.MultipartFile;


import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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

    List<LocationConstants> locationList=new ArrayList<>(Arrays.asList(LocationConstants.values()));

    UserController() {
        System.out.println("no arg const of controller");
    }

    @PostMapping("/signUp")
    public String signUp(Model model, @Valid UserDto userDto, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            boolean isSaved = userService.validAndSave(userDto);
            if (isSaved) {
                model.addAttribute("message", "SignUp Successful!");
                return "SignIn";
            }
        }
        model.addAttribute("error", bindingResult.getAllErrors());
        model.addAttribute("user", userDto);
        return "SignUp";
    }


    @GetMapping(value = "/signUpAgain")
    public String signUpAgain(Model model) {
        List<LocationConstants> locationList = new ArrayList<>(Arrays.asList(LocationConstants.values()));
        locationList.forEach(n -> System.out.println(n));
        model.addAttribute("locationListSend", locationList);
        return "SignUp";
    }

    @GetMapping(value = "/updateAgain")
    public String updateAgain(Model model) {
        List<LocationConstants> locationList = new ArrayList<>(Arrays.asList(LocationConstants.values()));
        locationList.forEach(n -> System.out.println(n));
        model.addAttribute("locationListSend", locationList);
        return "UpdateProfile";
    }


    @PostMapping(value = "/updateProfile")
    public String updateProfile(@RequestParam String email, @RequestParam String name, @RequestParam String location, @RequestParam Long altPhone, @RequestParam Long phone, @RequestParam String altEmail, @RequestParam("pic") MultipartFile multipartFile1, Model model) throws IOException {
        UserEntity updatedUser = userService.updateUserEntity(email, name, location, altPhone, phone, altEmail);

        if (updatedUser == null) {
            model.addAttribute("errorIs", "Profile update failed");
            model.addAttribute("locationListSend", locationList);
            model.addAttribute("user",updatedUser);
            return "UpdateProfile";
        }
        if (!multipartFile1.isEmpty()) {
                String filePath=saveImage(multipartFile1);
                updatedUser.setImagePath(filePath);
                userRepo.save(updatedUser);
                model.addAttribute("message", "Profile updated successfully with image.");
                return "UpdatedSuccess";
                }

        model.addAttribute("errorIsIn", "Image upload failed.");
        model.addAttribute("locationListSend", locationList);
        model.addAttribute("user",updatedUser);
        return "UpdateProfile";
    }

    private String saveImage(MultipartFile multipartFile) throws IOException {
        Path path = Paths.get("C:\\Users\\ASUS\\Desktop\\CommonModuleImages\\" + System.currentTimeMillis() + ".jpg");
        byte[] bytes = multipartFile.getBytes();
        Files.write(path, bytes);
        String filePath=path.getFileName().toString();
        System.out.println("filepath"+filePath);
        return filePath;
    }


   @PostMapping(value = "/signIn")
    public String signIn(@RequestParam String email, @RequestParam String password, Model model) {
        UserEntity userEntity = userService.getUserByEmail(email);
        if (userEntity == null) {
            System.out.println("user is found null");
            model.addAttribute("locationListSend", locationList);
            return "SignUp";
        }
        if (userEntity.getCount() == -1) {
            return "ResetPassword";
        }
       if (userEntity.getLocked()== true && userEntity.getLockTime() != null) {

           long hoursLocked = ChronoUnit.MINUTES.between(userEntity.getLockTime(), LocalDateTime.now());
           if (hoursLocked >= 5) {
               userEntity.setLocked(false);
               userEntity.setFailedAttempts(0);
               userEntity.setLockTime(null);
               userRepo.save(userEntity);
           } else {
               model.addAttribute("lock", "Your account is locked due to too many failed login attempts. Please try again later.");
               return "SignIn";
           }
       }
//        if (userEntity.getFailedAttempts() > 3) {
//             model.addAttribute("lock", "Your account is locked due to too many failed login attempts. Please try again later.");
//            return "SignUp";
//        }

        String storedPassword = userEntity.getPassword();
        if (!storedPassword.equals(password)) {
            userEntity.setFailedAttempts(userEntity.getFailedAttempts() + 1);

            if (userEntity.getFailedAttempts() > 3) {
                userEntity.setLocked(true);
                userEntity.setLockTime(LocalDateTime.now());
                userRepo.save(userEntity);
                model.addAttribute("say", "Your Account has locked try again after 24 hours");
                return "SignIn";
            } else {
                userRepo.save(userEntity);
                int remainingAttempts = 3 - userEntity.getFailedAttempts();
                model.addAttribute("error", "Incorrect password. You have " + remainingAttempts + " attempts remaining.");
                return "SignIn";
            }
        } else {
            userEntity.setFailedAttempts(0);
            userRepo.save(userEntity);
        }
        //model.addAttribute("name", userEntity.getName());
        return "Success";
    }


    @PostMapping("/resetPassword")
    public String resetPassword(@RequestParam String email, @RequestParam String newPassword, Model model, String oldPassword) {
        UserEntity userEntity = userService.getUserByEmail(email);

        if (userEntity != null) {
            if (userEntity.getCount() == -1) {
                if (userEntity.getPassword().equals(oldPassword)) {
                    userEntity.setPassword(String.valueOf(newPassword));
                    userEntity.setCount(1);
                    userRepo.save(userEntity);
                    return "Success";
                } else {
                    return "ResetPassword";

                }
            } else {

                return "SignIn";
            }
        } else {

            return "SignUp";
        }
    }


}
