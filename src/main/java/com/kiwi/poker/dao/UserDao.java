package com.kiwi.poker.dao;

import com.kiwi.poker.domain.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<UserEntity, String> {

    UserEntity getByUserId(String userId);

    UserEntity getByUsernameAndPassword(String username, String password);
}
