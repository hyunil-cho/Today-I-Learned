package com.crud.group.web.controller.user;

public class ResponseForNewUser {
    private final String message;
    private final long userId;

    public ResponseForNewUser(String message, long userId) {
        this.message = message;
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public long getUserId() {
        return userId;
    }
}
