package com.ssacademy.healthcare.system.api;

import com.ssacademy.healthcare.system.dto.request.RequestDoctorDto;
import com.ssacademy.healthcare.system.dto.request.RequestUserDto;
import com.ssacademy.healthcare.system.service.UserService;
import com.ssacademy.healthcare.system.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/visitor/signup")
    public ResponseEntity<StandardResponse> createDoctor(@RequestBody RequestUserDto userDto){
        userService.signup(userDto);
        return new ResponseEntity<>(
                new StandardResponse(201,"User was saved",userDto.getFullname()),
                HttpStatus.CREATED
        );
    }
    @GetMapping(value = "/verify", params = {"type"})
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DOCTOR')")
    public ResponseEntity<StandardResponse> verifyUser(
            @RequestParam String type,
            @RequestHeader("Authorization") String token
    ) {
        return new ResponseEntity<>(
                new StandardResponse(200,"User state",userService.verifyUser(type, token)),
                HttpStatus.OK
        );
    }

}
