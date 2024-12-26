package com.xworkz.commoun_module.controller;
import com.xworkz.commoun_module.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class UserRestController {
    @Autowired
    private UserService service;

    @GetMapping(value = "/name/{nam}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String onName(@PathVariable String nam) {
        System.out.println("name=" + nam);
        Long count = this.service.countName(nam);
        if (count == 0) {
            System.out.println("does not exist");
            return "This username is available.";
        } else{
            System.out.println("exists");
        return "This username is already in use.";
    }
}



    @GetMapping(value = "/email1/{em}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String onEmail(@PathVariable String em) {
        System.out.println("email=" + em);
        Long count = this.service.countByEmail(em);
        if (count == 0) {
            return "does not exist";
        } else
            return  "exists";
    }

    @GetMapping(value = "/altEmail1/{altEmai}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String onAltEmail(@PathVariable String altEmai) {
        System.out.println("altEmail=" + altEmai);
        Long count = this.service.countAltEmail(altEmai);
        System.out.println("altEmai"+altEmai);
        if (count == 0) {
            return "does not exist";
        } else
            return "exists";
    }

    @GetMapping(value = "/phone1/{phon}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String onPhone(@PathVariable long phon) {
        System.out.println("phone=" + phon);
        Long count = this.service.countByPhone(phon);
        if (count == 0) {
            return "does not exist";
        } else
           return  "exists";
    }

    @GetMapping(value = "/altPhone1/{altPhon}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String onAltPhone(@PathVariable long altPhon) {
        System.out.println("altPhone=" + altPhon);
        Long count = this.service.countByAltPhone(altPhon);
        if (count == 0) {
            return "does not exist";
        } else
            return "exists";

    }

    @GetMapping(value = "/location/{locati}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String onLocation(@PathVariable String locati) {
        System.out.println("location=" + locati);
        Long count = this.service.countByLocation(locati);
        if (count == 0) {
            return "does not exist";
        } else
            return "exists";
    }
}
