package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.UserModel;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/signup")
public class SingUpController {



    private final UserService userService ;




    @GetMapping
    public String signupView( @ModelAttribute("User_model") UserModel userModel, Model model) {
        model.addAttribute("signupSuccess" , false);
        model.addAttribute("signupError" , false);
        return "signup";
    }


    @PostMapping
    public String createUser(@ModelAttribute("User_model") UserModel userModel , Model model){
        boolean isUsernameAvailable = userService.isUsernameAvailable(userModel.getUsername());
        if(isUsernameAvailable){
            userService.createUser(userModel);
            model.addAttribute("signupSuccess" , true);
        }else{
            model.addAttribute("signupError" , true);
            model.addAttribute("signupErrorMassage" , "Username not available");
        }

        return "signup";

    }

}
