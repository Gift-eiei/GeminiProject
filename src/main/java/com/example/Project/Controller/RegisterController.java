package com.example.Project.Controller;

import com.example.Project.Controller.Object.RegisObj;
import com.example.Project.Model.User.User;
import com.example.Project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
@RequestMapping("/registration")
public class RegisterController {


    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public RegisObj userRegisObj() {
        return new RegisObj();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid RegisObj regisObj,
                                      BindingResult result, Errors errors) {

        User existing = userService.findByEmail(regisObj.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        if (errors.hasErrors()) {
            return "registration";
        }

        userService.save(regisObj);
        return "redirect:/registration?success";
    }


}
