package com.kiwi.poker.service;

public interface UserService {
    String login(String name, String pwd);

    boolean register(String name, String pwd);

    boolean getChips(String name);
}
