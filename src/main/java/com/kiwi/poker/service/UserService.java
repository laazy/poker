package com.kiwi.poker.service;

import com.kiwi.poker.dto.auth.LoginForm;
import com.kiwi.poker.dto.auth.LoginResponse;
import com.kiwi.poker.dto.auth.RegisterForm;
import com.kiwi.poker.dto.auth.RegisterResponse;

public interface UserService {


    Integer getChips(String userId);

    boolean setChips(String userId, Integer chips);

    /**
     * update chips when finishing a game
     * @param userId userId
     * @param delta positive for increment, negative for decrement
     * @return false iff cur_chips + delta < 0
     */
    boolean updateChips(String userId, Integer delta);

    LoginResponse login(LoginForm form);

    RegisterResponse register(RegisterForm form);
}
