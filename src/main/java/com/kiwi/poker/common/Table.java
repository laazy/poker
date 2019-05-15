package com.kiwi.poker.common;

public interface Table {
    String addPlayer(String id);

    String removePlayer(String id);

    void startGame();

    String endGame();
}
