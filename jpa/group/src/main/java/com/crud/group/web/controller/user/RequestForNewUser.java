package com.crud.group.web.controller.user;

import com.crud.group.core.usecase.user.SaveUserRequest;

public class RequestForNewUser {

    private final String userName;
    private final String userPassword;

    public RequestForNewUser(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = encode(userPassword);
    }

    private String encode(String userPassword) {
        return userPassword.toUpperCase();
    }

    public SaveUserRequest to(){
        return new SaveUserRequest(this.userName, this.userPassword);
    }


}
