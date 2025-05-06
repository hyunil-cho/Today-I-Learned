package com.crud.group.web.controller.post.view;

import com.crud.group.core.usecase.post.view.ViewPostRequest;
import com.crud.group.web.controller.common.HttpReq;

public class RequestForViewPosts extends HttpReq {
    private final int startPage;
    private final int size;

    public RequestForViewPosts(int startPage, int size) {
        this.startPage = startPage;
        this.size = size;
    }

    public int getStartPage() {
        return startPage;
    }

    public int getSize() {
        return size;
    }

    public ViewPostRequest to() {
        return new ViewPostRequest(this.startPage, this.size);
    }
}
