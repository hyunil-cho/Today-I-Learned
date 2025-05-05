package com.crud.group.core.usecase.post;

import com.crud.group.core.usecase.Request;

public class PostRequest extends Request {
    private final String title;
    private final String body;
    private final long authorId;

    public PostRequest(String title, String body, long authorId) {
        this.title = title;
        this.body = body;
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public long getAuthorId() {
        return authorId;
    }
}
