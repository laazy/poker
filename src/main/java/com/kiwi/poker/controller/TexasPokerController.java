package com.kiwi.poker.controller;

import com.kiwi.poker.service.TexasPokerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/texas")
@CrossOrigin
public class TexasPokerController {

    @Autowired
    private TexasPokerService texasPokerService;

    @GetMapping("/onlinenum")
    public Integer getOnlineNum() {
        return texasPokerService.getOnlineNum();
    }

}
