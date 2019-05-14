package com.kiwi.poker.service;

public interface UserService {
    String login(String name, String pwd);

    String register(String name, String pwd);

    Integer getChips(String userId);

    Integer setChips(String userId, Integer chips);

    /**
     * update chips when finishing a game
     * @param userId userId
     * @param delta positive for increment, negative for decrement
     * @return false iff cur_chips + delta < 0
     */
    boolean updateChips(String userId, Integer delta);
}
