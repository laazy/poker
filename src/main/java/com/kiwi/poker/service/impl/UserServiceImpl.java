package com.kiwi.poker.service.impl;

import com.kiwi.poker.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public String login(String name, String pwd) {
        return null;
    }

    @Override
    public String register(String name, String pwd) {
        return null;
    }

    @Override
    public Integer getChips(String userId) {
        return null;
    }

    @Override
    public Integer setChips(String userId, Integer chips) {
        return null;
    }

    @Override
    public boolean updateChips(String userId, Integer delta) {
        return false;
    }
}
