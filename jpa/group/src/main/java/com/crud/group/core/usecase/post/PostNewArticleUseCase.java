package com.crud.group.core.usecase.post;

import com.crud.group.core.entity.*;
import com.crud.group.core.usecase.UseCase;
import jakarta.transaction.Transactional;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostNewArticleUseCase implements UseCase<PostRequest, PostResponse> {

    private final PostInfoRepository postInfoRepository;
    private final UsersInfoRepository usersInfoRepository;
    public PostNewArticleUseCase(PostInfoRepository postInfoRepository, UsersInfoRepository usersInfoRepository) {
        this.postInfoRepository = postInfoRepository;
        this.usersInfoRepository = usersInfoRepository;
    }

    @Transactional
    @Override
    public PostResponse handle(PostRequest postRequest) {


        Optional<Users> authorUser = this.usersInfoRepository.findById(postRequest.getAuthorId());

        if(authorUser.isPresent()){

            PostInfo newPostIno = new PostInfo();
            newPostIno.setStatus(PostStatus.POSTED);
            newPostIno.setBody(postRequest.getBody());
            newPostIno.setTitle(postRequest.getTitle());
            newPostIno.setAuthor(authorUser.get());

            PostInfo result = postInfoRepository.save(newPostIno);
            return new PostResponse(200, result.getTitle()+" is successfully created");
        }

        return new PostResponse(404, postRequest.getAuthorId()+" is not found user");

    }
}
