package com.kiwi.poker.dto.auth;

import com.kiwi.poker.dto.BaseResponse;

public class LoginResponse extends BaseResponse {

    private String userId;
    private Integer chips;

    public LoginResponse(Integer status, String description, String userId, Integer chips) {
        super(status, description);
        this.userId = userId;
        this.chips = chips;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getChips() {
        return chips;
    }

    public void setChips(Integer chips) {
        this.chips = chips;
    }
}
