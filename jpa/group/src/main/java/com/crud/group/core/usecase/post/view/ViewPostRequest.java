package com.crud.group.core.usecase.post.view;


import com.crud.group.core.usecase.Request;

public class ViewPostRequest extends Request {

    private final int startPage;
    private final int size;


    public ViewPostRequest(int startPage, int size) {
        this.startPage = startPage;
        this.size = size;
    }

    public int getStartPage() {
        return startPage;
    }

    public int getSize() {
        return size;
    }
}
