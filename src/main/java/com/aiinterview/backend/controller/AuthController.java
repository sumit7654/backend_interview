package com.aiinterview.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.aiinterview.backend.model.User;
import com.aiinterview.backend.service.AuthService;

@RestController //Spring ko bolta hai:“Ye class REST API handle karegi”


@RequestMapping("/api") //Common base path Isliye endpoint banega:

public class AuthController {

    @Autowired
    private AuthService authService;

    // REGISTER API
    @PostMapping("/register") //New data create karne ke liye POST use hota hai
    public String register(@RequestBody User user) {
        return authService.register(user);
    }
    
    // LOGIN API
    @PostMapping("/login")
    public String login(@RequestBody User user) {
    return authService.login(user);
}

}
