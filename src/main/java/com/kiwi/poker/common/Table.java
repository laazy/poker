package com.kiwi.poker.common;

public interface Table {
    String addPlayer(String id, Integer pos);

    String removePlayer(String id);

    void startGame();

    String endGame();
}
