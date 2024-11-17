package com.cachedb.server.models;

import java.time.Instant;
import java.time.LocalDateTime;

public class UserContent {
    private String key;
    private String userName;
    private String userData;
    Instant expirationDate;
    private boolean isExpiredState;

    public UserContent(int expirationDate, String userData, String userName, String key) {
        this.expirationDate = Instant.now().plusSeconds(expirationDate);
        this.userData = userData;
        this.userName = userName;
        this.key = key;
        this.isExpiredState = false;
    }


    public String getKey() {
        return key;
    }

    public String getUserName() {
        return userName;
    }

    public boolean isExpired() {
        if (Instant.now().isAfter(expirationDate)) {
            this.isExpiredState = true;
            return true;
        }
        return false;
    }

    public String getUserData() {
        return userData;
    }

    public Instant getExpirationDate() {
        return expirationDate;
    }

    @Override
    public String toString() {
        return "{" +
                "key='" + key + '\'' +
                ", userName='" + userName + '\'' +
                ", userData='" + userData + '\'' +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
