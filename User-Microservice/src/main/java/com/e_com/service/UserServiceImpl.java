package com.e_com.service;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.e_com.entity.AddressEntity;
import com.e_com.entity.RoleEntity;
import com.e_com.entity.UserEntity;
import com.e_com.repositories.RoleRepo;
import com.e_com.repositories.UserRepo;
import com.e_com.request.UsersRequest;
import com.e_com.response.AddressResponse;
import com.e_com.response.UserResponse;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private ModelMapper modelMapper;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // ---------------- ADD USER ----------------
    @Override
    public UserResponse addUser(UsersRequest request) {

        // Map request to entity
        UserEntity user = new UserEntity();
        user.setUserFirstName(request.getUserFirstName());
        user.setUserLastName(request.getUserLastName());
        user.setUserName(request.getUserName());
        user.setEmailId(request.getEmailId());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        // Assign default role
        RoleEntity role = roleRepo.findByRoleName("USER");
        user.setRole(role);

        // Map addresses
        List<AddressEntity> addressEntities = request.getAddress().stream().map(addr -> {
            AddressEntity address = new AddressEntity();
            address.setFullName(addr.getFullName());
            address.setPhoneNumber(addr.getPhoneNumber());
            address.setAddressLine1(addr.getAddressLine1());
            address.setAddressLine2(addr.getAddressLine2());
            address.setCity(addr.getCity());
            address.setState(addr.getState());
            address.setCountry(addr.getCountry());
            address.setPincode(addr.getPincode());
            address.setDefault(addr.isDefault());
            address.setUser(user);
            return address;
        }).toList();

        user.setAddresses(addressEntities);

        // Save user
        UserEntity savedUser = userRepo.save(user);

        // Convert to response
        return mapToResponse(savedUser);
    }

    // ---------------- FIND USER BY ID ----------------
    @Override
    public UserResponse findById(Long userId) {

        UserEntity user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return mapToResponse(user);
    }

    // ---------------- DELETE USER ----------------
    @Override
    public UserResponse deleteUser(Long userId) {

        UserEntity user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userRepo.delete(user);

        return mapToResponse(user);
    }

    // ---------------- MAPPING METHOD ----------------
    private UserResponse mapToResponse(UserEntity user) {

        UserResponse response = modelMapper.map(user, UserResponse.class);

        // Set role name
        response.setRole(user.getRole().getRoleName());

        // Map addresses
        List<AddressResponse> addressResponses = user.getAddresses().stream()
                .map(address -> modelMapper.map(address, AddressResponse.class))
                .toList();

        response.setAddresses(addressResponses);

        return response;
    }
}
