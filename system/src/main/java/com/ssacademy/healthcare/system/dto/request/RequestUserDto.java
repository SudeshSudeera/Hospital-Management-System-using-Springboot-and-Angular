package com.ssacademy.healthcare.system.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestUserDto {
    private long id;
    private String fullname;
    private String email;
    private String password;
}
