package com.crud.group.core.usecase.user;

import com.crud.group.core.usecase.Response;

public class SaveUserResponse extends Response {
    private final long userId;
    protected SaveUserResponse(int statusCode, String message, long userId) {
        super(statusCode, message);
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }
}
