package com.crud.group.core.usecase.post.newpost;

import com.crud.group.core.usecase.Response;
import com.crud.group.core.usecase.Resultable;

public class PostResponse extends Resultable {
    public PostResponse(int statusCode, String message) {
        super(statusCode, message);
    }

}
