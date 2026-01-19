package com.e_com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.e_com.request.UsersRequest;
import com.e_com.response.UserResponse;
import com.e_com.service.IUserService;

import jakarta.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private IUserService service;

    // ---------------- CREATE USER ----------------
    @PostMapping("/users")
    public ResponseEntity<UserResponse> addUser(@Valid @RequestBody UsersRequest request) {

        UserResponse response = service.addUser(request);
        return ResponseEntity.ok(response);
    }

    // ---------------- GET USER BY ID ----------------
    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponse> findByUserId(@PathVariable Long id) {

        UserResponse response = service.findById(id);
        return ResponseEntity.ok(response);
    }

    // ---------------- DELETE USER ----------------
    @DeleteMapping("/deleteUsers/{id}")
    public ResponseEntity<UserResponse> deleteUser(@PathVariable Long id) {

        UserResponse response = service.deleteUser(id);
        return ResponseEntity.ok(response);
    }
}
