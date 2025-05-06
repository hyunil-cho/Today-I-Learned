package com.crud.group.core.usecase.post.view;

import com.crud.group.core.entity.PostInfo;
import com.crud.group.core.usecase.Response;
import com.crud.group.core.usecase.Resultable;

import java.util.Collections;
import java.util.List;

public class ViewPostResponse extends Resultable {

    private final int totalPage;
    private final int size;
    private final long totalSize;
    private final List<PostInfo> contents;

    protected ViewPostResponse(int statusCode, String message, int totalPage, int size, long totalSize, List<PostInfo> contents) {
        super(statusCode, message);
        this.totalPage = totalPage;
        this.size = size;
        this.totalSize = totalSize;
        this.contents = contents;
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
        return Collections.unmodifiableList(contents);
    }
}
