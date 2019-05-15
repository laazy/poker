package com.kiwi.poker.dto.auth;

import com.kiwi.poker.dto.BaseResponse;

public class RegisterResponse extends BaseResponse {
    private String userId;

    public RegisterResponse(Integer status, String description, String userId) {
        super(status, description);
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
