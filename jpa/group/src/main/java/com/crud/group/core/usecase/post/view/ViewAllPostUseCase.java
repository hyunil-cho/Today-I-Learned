package com.crud.group.core.usecase.post.view;

import com.crud.group.core.entity.PostInfo;
import com.crud.group.core.entity.PostInfoRepository;
import com.crud.group.core.usecase.UseCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ViewAllPostUseCase implements UseCase<ViewPostRequest, ViewPostResponse> {

    private final PostInfoRepository repository;

    public ViewAllPostUseCase(PostInfoRepository repository) {
        this.repository = repository;
    }
    @Override
    public ViewPostResponse handle(ViewPostRequest viewPostRequest) {

        PageRequest pageRequest = PageRequest.of(viewPostRequest.getStartPage(), viewPostRequest.getSize());
        Page<PostInfo> result = repository.findAll(pageRequest);

        return new ViewPostResponse(
                200, "successfull",
                result.getTotalPages(),
                result.getSize(),
                result.getTotalElements(),
                result.getContent());
    }
}
