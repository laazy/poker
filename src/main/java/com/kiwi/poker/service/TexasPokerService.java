package com.kiwi.poker.service;

public interface TexasPokerService {
    String fold(String id);

    String raise(String id, Integer chip);
}
