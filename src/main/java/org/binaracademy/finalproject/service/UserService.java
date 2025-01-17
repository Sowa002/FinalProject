package org.binaracademy.finalproject.service;

import org.binaracademy.finalproject.model.response.GetUserResponse;
import org.binaracademy.finalproject.model.response.UserResponse;
import org.binaracademy.finalproject.model.request.UpdateUserRequest;

import java.util.List;

public interface UserService {

    UserResponse updateUsers(UpdateUserRequest updateUsers);

    void deleteUsersByUsername();

    List<GetUserResponse> getAllUser();

    UserResponse getUser();

    void deleteUserForAdmin(String username);
}