package com.crud.group.web.controller.post.newPost;


public class ResponseForSavingPost {

    private final String message;

    public ResponseForSavingPost(String message) {
        this.message = message;
    }


    public String getMessage() {
        return message;
    }

}
