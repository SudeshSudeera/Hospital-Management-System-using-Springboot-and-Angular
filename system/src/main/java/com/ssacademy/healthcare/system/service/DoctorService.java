package com.ssacademy.healthcare.system.service;

import com.ssacademy.healthcare.system.dto.request.RequestDoctorDto;
import com.ssacademy.healthcare.system.dto.response.ResponseDoctorDto;
import com.ssacademy.healthcare.system.dto.response.paginated.PaginatedDoctorResponseDto;

public interface DoctorService {
    public void createDoctor(RequestDoctorDto dto);
    public ResponseDoctorDto getDoctor(long id);

    /*public List<ResponseDoctorDto> findDoctorByName(String name);*/

    public void deleteDoctor(long id);
    public void updateDoctor(long id, RequestDoctorDto dto);
    public PaginatedDoctorResponseDto getAllDoctors(String searchText, int page, int size);
}
