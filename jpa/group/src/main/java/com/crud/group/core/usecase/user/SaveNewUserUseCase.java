package com.crud.group.core.usecase.user;

import com.crud.group.core.entity.Users;
import com.crud.group.core.entity.UsersInfoRepository;
import com.crud.group.core.usecase.UseCase;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class SaveNewUserUseCase implements UseCase<SaveUserRequest, SaveUserResponse> {

    private final UsersInfoRepository usersInfoRepository;

    public SaveNewUserUseCase(UsersInfoRepository usersInfoRepository) {
        this.usersInfoRepository = usersInfoRepository;
    }

    @Override
    public SaveUserResponse handle(SaveUserRequest saveUserRequest) {

        Users user = saveUserRequest.to();
        //TODO :: 예외 처리 관련 코드 추가
        this.usersInfoRepository.save(user);        

        return new SaveUserResponse(200, "successfully created new User", user.getId());
    }
}
