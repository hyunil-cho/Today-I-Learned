package com.crud.group.web.controller.post.view;

import com.crud.group.core.entity.PostInfo;
import com.crud.group.core.usecase.Response;
import com.crud.group.core.usecase.post.view.ViewPostResponse;
import com.crud.group.web.controller.common.HttpResponse;

import java.util.List;

public class ResponseForViewPost extends HttpResponse {
    private final int totalPage;
    private final int size;
    private final long totalSize;
    private final List<PostInfo> contents;


    public ResponseForViewPost(ViewPostResponse result) {
        super(result.getResponse().getStatusCode(), result.getResponse().getMeesage());
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

