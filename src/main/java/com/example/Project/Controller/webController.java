package com.example.Project.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class webController {

    @GetMapping("/")
    public String root() {
        return "index";
    }


    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    public String userIndex() {
        return "user/index";
    }


}
