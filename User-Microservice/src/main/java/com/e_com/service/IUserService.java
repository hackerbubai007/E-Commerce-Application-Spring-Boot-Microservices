package com.e_com.service;

import org.springframework.stereotype.Service;

import com.e_com.request.UsersRequest;
import com.e_com.response.UserResponse;

@Service
public interface IUserService {

	public UserResponse addUser(UsersRequest request);

	public UserResponse deleteUser(Long userId);

	public UserResponse findById(Long userId);

}
