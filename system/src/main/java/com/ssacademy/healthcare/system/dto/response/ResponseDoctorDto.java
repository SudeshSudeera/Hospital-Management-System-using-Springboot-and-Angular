package com.ssacademy.healthcare.system.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class ResponseDoctorDto {
    private long id;
    private String name;
    private String address;
    private String contact;
    private double salary;
}
