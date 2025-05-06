package com.crud.group.web.controller.post.view;

import com.crud.group.core.entity.PostInfo;
import com.crud.group.core.usecase.post.view.ViewPostResponse;

import java.util.List;

public class ResponseForViewPost {
    private final int totalPage;
    private final int size;
    private final long totalSize;
    private final List<PostInfo> contents;


    public ResponseForViewPost(int totalPage, int size, long totalSize, List<PostInfo> contents) {
        this.totalPage = totalPage;
        this.size = size;
        this.totalSize = totalSize;
        this.contents = contents;
    }

    public ResponseForViewPost(ViewPostResponse result) {
        this.size = result.getSize();
        this.contents = result.getContents();
        this.totalSize = result.getTotalSize();
        this.totalPage = result.getTotalPage();
    }

    public int getTotalPage() {
        return totalPage;
    }

    public int getSize() {
        return size;
    }

    public long getTotalSize() {
        return totalSize;
    }

    public List<PostInfo> getContents() {
        return contents;
    }
}

