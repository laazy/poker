package com.kiwi.poker.controller;

import com.kiwi.poker.constant.ResponseConst;
import com.kiwi.poker.dto.auth.LoginForm;
import com.kiwi.poker.dto.auth.LoginResponse;
import com.kiwi.poker.dto.auth.RegisterForm;
import com.kiwi.poker.dto.auth.RegisterResponse;
import com.kiwi.poker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public LoginResponse login(LoginForm form, HttpSession session) {
        LoginResponse response = userService.login(form);
        if (response.getStatus() == ResponseConst.SUCCESS) {
            session.setAttribute("userId", response.getUserId());
        }
        return response;
    }

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterForm form) {
        return userService.register(form);
    }

    @GetMapping("/testSession")
    public String testSession(HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        System.out.println("[auth/testSession] userId: " + userId);
        return userId;
    }
}
