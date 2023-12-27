package com.ssacademy.healthcare.system.service;

import com.ssacademy.healthcare.system.dto.request.RequestUserDto;

public interface UserService {
    public void signup(RequestUserDto requestUserDto);
    public boolean verifyUser(String type, String token);
}
