package cz.uhk.kppro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping("/")
    public String signIn(){
        return "login";
    }

    @RequestMapping("/new")
    public String signUp(){
        return "sign_up";
    }
}
