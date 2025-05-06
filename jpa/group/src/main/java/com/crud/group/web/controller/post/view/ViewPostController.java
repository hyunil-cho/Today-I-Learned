package com.crud.group.web.controller.post.view;

import com.crud.group.core.entity.PostInfoRepository;
import com.crud.group.core.usecase.post.view.ViewAllPostUseCase;
import com.crud.group.core.usecase.post.view.ViewPostResponse;
import com.crud.group.web.controller.post.newPost.ResponseForSavingPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
public class ViewPostController {

    private final ViewAllPostUseCase useCase;

    public ViewPostController(ViewAllPostUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping
    public ResponseForViewPost listPostWithPageable(@RequestBody RequestForViewPosts viewPosts){

        ViewPostResponse result = this.useCase.handle(viewPosts.to());
        return new ResponseForViewPost(result);

    }

}
