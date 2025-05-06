package com.crud.group.web.controller.user;

import com.crud.group.core.usecase.Response;
import com.crud.group.web.controller.common.HttpResponse;

public class ResponseForNewUser extends HttpResponse {
    private final long userId;

    public ResponseForNewUser(Response message, long userId) {
        super(message.getStatusCode(), message.getMeesage());
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }
}
