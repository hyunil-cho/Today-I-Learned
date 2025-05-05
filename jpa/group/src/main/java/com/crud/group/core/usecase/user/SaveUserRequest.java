package com.crud.group.core.usecase.user;

import com.crud.group.core.entity.Users;
import com.crud.group.core.usecase.Request;

public class SaveUserRequest extends Request {
    private final String userName;
    private final String password;

    public SaveUserRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public Users to(){
        return new Users(this.userName, this.password);
    }
}
