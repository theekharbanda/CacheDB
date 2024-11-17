package com.cachedb.server.models;

import java.time.LocalDateTime;

public class User {
    private String userName;
    private String userData;
    private LocalDateTime expirationTime;


    public User(String userName, String userData, LocalDateTime expirationTime) {
        this.userName = userName;
        this.userData = userData;
        this.expirationTime = expirationTime;
    }
    public User(String userName, String userData) {
        this.userName = userName;
        this.userData = userData;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserData() {
        return userData;
    }

    public void setUserData(String userData) {
        this.userData = userData;
    }

    @Override
    public String toString() {
        return "{" +
                "userName:'" + userName + '\'' +
                ", userData:'" + userData + '\'' +
                '}';
    }
}
