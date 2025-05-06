package com.crud.group.web.controller.post.newPost;

import com.crud.group.core.usecase.post.newpost.PostRequest;
import com.crud.group.web.controller.common.HttpReq;

public class RequestForSavingPost extends HttpReq {
    private final String title;
    private final String body;
    private final long authorId;


    public RequestForSavingPost(String title, String body, long authorId) {
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

    public PostRequest to(){
        return new PostRequest(this.title, this.body, this.authorId);
    }
}
