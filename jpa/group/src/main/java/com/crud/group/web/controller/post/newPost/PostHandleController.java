package com.crud.group.web.controller.post.newPost;

import com.crud.group.core.usecase.UseCase;
import com.crud.group.core.usecase.post.newpost.PostRequest;
import com.crud.group.core.usecase.post.newpost.PostResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
public class PostHandleController {

    private final UseCase<PostRequest, PostResponse> postUsecase;

    public PostHandleController(UseCase<PostRequest, PostResponse> postUsecase) {
        this.postUsecase = postUsecase;
    }


    @PostMapping
    public ResponseEntity<ResponseForSavingPost> savePost(@RequestBody RequestForSavingPost request){

        PostRequest postRequest = request.to();
        PostResponse result = this.postUsecase.handle(postRequest);

        if(result.isSuccessFull()){
            return ResponseEntity.ok(new ResponseForSavingPost(result.getMeesage()));
        }else if(result.isClientError()){
            return ResponseEntity.badRequest().body(new ResponseForSavingPost(result.getMeesage()));
        }

        return  ResponseEntity.internalServerError().body(new ResponseForSavingPost(result.getMeesage()));

    }

}
