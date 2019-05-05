package com.kiwi.poker.service;

public interface TexasPokerService {
    String enter(String id);

    String quit(String id);

    String fold(String id);

    String raise(String id, Integer chip);
}
