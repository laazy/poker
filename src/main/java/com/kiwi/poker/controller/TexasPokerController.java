package com.kiwi.poker.controller;

import com.kiwi.poker.service.TexasPokerService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.WebSocketSession;

import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("/texas")
@CrossOrigin
public class TexasPokerController {

    @Autowired
    private TexasPokerService texasPokerService;

    @PostMapping("/setSession")
    public Integer setSession(HttpSession session) {
        session.setAttribute("userId", "123");
        return 0;
    }

    @GetMapping("/onlinenum")
    public Integer getOnlineNum(HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        System.out.println(userId);
//        session.setAttribute("userId", "123");
        return 0;
    }

}
