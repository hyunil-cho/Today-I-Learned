package com.crud.group.web.controller.post.newPost;


import com.crud.group.core.usecase.Response;
import com.crud.group.web.controller.common.HttpResponse;

public class ResponseForSavingPost extends HttpResponse {

    private final String message;

    public ResponseForSavingPost(Response response, String message) {
        super(response.getStatusCode(), response.getMeesage());
        this.message = message;
    }


    public String getMessage() {
        return message;
    }

}
