package com.kiwi.poker.service.impl;

import com.kiwi.poker.constant.ResponseConst;
import com.kiwi.poker.dao.UserDao;
import com.kiwi.poker.domain.UserEntity;
import com.kiwi.poker.dto.auth.LoginForm;
import com.kiwi.poker.dto.auth.LoginResponse;
import com.kiwi.poker.dto.auth.RegisterForm;
import com.kiwi.poker.dto.auth.RegisterResponse;
import com.kiwi.poker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Integer getChips(String userId) {
        UserEntity entity = userDao.getByUserId(userId);
        if (entity == null) {
            return -1;
        }
        return entity.getChips();
    }

    @Override
    public boolean setChips(String userId, Integer chips) {
        UserEntity entity = userDao.getByUserId(userId);
        if (entity == null || chips < 0) {
            return false;
        }
        entity.setChips(chips);
        userDao.save(entity);
        return true;
    }

    @Override
    public boolean updateChips(String userId, Integer delta) {
        UserEntity entity = userDao.getByUserId(userId);
        if (entity == null) {
            return false;
        }

        int chips = entity.getChips() + delta;
        if (chips < 0) {
            return false;
        }

        entity.setChips(chips);
        userDao.save(entity);
        return true;
    }

    @Override
    public LoginResponse login(LoginForm form) {
        if (form == null || form.getUsername() == null || form.getPassword() == null) {
            return new LoginResponse(ResponseConst.MISSING_PARAMETER, "missing parameter", null, null);
        }
        LoginResponse response = new LoginResponse(ResponseConst.GENERAL_PURPOSE_FAILURE, "", null, null);
        UserEntity entity = userDao.getByUsernameAndPassword(form.getUsername(), form.getPassword());
        if (entity != null) {
            response.setStatus(ResponseConst.SUCCESS);
            response.setUserId(entity.getUserId());
            response.setChips(entity.getChips());
        }
        return response;
    }

    @Override
    public RegisterResponse register(RegisterForm form) {
        if (form == null || form.getUsername() == null || form.getPassword() == null) {
            return new RegisterResponse(ResponseConst.MISSING_PARAMETER, "missing parameter", null);
        }
        RegisterResponse response = new RegisterResponse(ResponseConst.GENERAL_PURPOSE_FAILURE, "", null);
        UserEntity entity = new UserEntity();
        entity.setUsername(form.getUsername());
        entity.setPassword(form.getPassword());
        entity.setChips(1000000);
        try {
            userDao.save(entity);
        } catch (Exception e) {
            return response;
        }
        response.setUserId(entity.getUserId());
        response.setStatus(ResponseConst.SUCCESS);
        return response;
    }
}
