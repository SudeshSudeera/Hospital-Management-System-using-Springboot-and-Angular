package com.ssacademy.healthcare.system.util.mapper;

import com.ssacademy.healthcare.system.dto.request.RequestDoctorDto;
import com.ssacademy.healthcare.system.dto.response.ResponseDoctorDto;
import com.ssacademy.healthcare.system.entity.Doctor;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface DoctorMapper {
    ResponseDoctorDto toResponseDoctorDto(Doctor doctor);
    Doctor toDoctor(RequestDoctorDto dto);
    List<ResponseDoctorDto> toResponseDoctorDtoList(List<Doctor> list);


}
