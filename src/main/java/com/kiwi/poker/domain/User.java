package com.kiwi.poker.domain;

import org.springframework.web.socket.WebSocketSession;

// TODO: 和 @link{Player} 的关系
public class User {
    private String userid;
    private String username;
    private WebSocketSession session;

    public User(String userid, WebSocketSession session) {
        this.userid = userid;
        this.session = session;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public WebSocketSession getSession() {
        return session;
    }

    public void setSession(WebSocketSession session) {
        this.session = session;
    }
}
