package com.crud.group.web.controller.user;

import com.crud.group.core.usecase.user.SaveNewUserUseCase;
import com.crud.group.core.usecase.user.SaveUserRequest;
import com.crud.group.core.usecase.user.SaveUserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserManageController {

    private final SaveNewUserUseCase usecase;

    public UserManageController(SaveNewUserUseCase usecase) {
        this.usecase = usecase;
    }

    @PostMapping
    public ResponseEntity<ResponseForNewUser> saveNewUser(@RequestBody RequestForNewUser req){

        SaveUserRequest saveUserRequest = req.to();
        SaveUserResponse result = this.usecase.handle(saveUserRequest);

        if(result.isSuccessFull()){
            return ResponseEntity.ok(new ResponseForNewUser(result.getMeesage(), result.getUserId()));
        }else if(result.isClientError()){
            return ResponseEntity.badRequest().body(new ResponseForNewUser(result.getMeesage(), result.getUserId()));
        }

        return  ResponseEntity.internalServerError().body(new ResponseForNewUser(result.getMeesage(),result.getUserId()));

    }


}
