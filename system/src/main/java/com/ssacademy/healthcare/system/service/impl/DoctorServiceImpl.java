package com.ssacademy.healthcare.system.service.impl;

import com.ssacademy.healthcare.system.dto.request.RequestDoctorDto;
import com.ssacademy.healthcare.system.dto.response.ResponseDoctorDto;
import com.ssacademy.healthcare.system.dto.response.paginated.PaginatedDoctorResponseDto;
import com.ssacademy.healthcare.system.entity.Doctor;
import com.ssacademy.healthcare.system.exceptions.EntryNotFoundException;
import com.ssacademy.healthcare.system.repo.DoctorRepo;
import com.ssacademy.healthcare.system.service.DoctorService;
import com.ssacademy.healthcare.system.util.mapper.DoctorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepo doctorRepo;
    private final DoctorMapper doctorMapper;

    @Autowired
    public DoctorServiceImpl(DoctorRepo doctorRepo, DoctorMapper doctorMapper) {
        this.doctorRepo = doctorRepo;
        this.doctorMapper = doctorMapper;
    }

    @Override
    public void createDoctor(RequestDoctorDto dto) {

        UUID uuid = UUID.randomUUID();
        long docId = uuid.getMostSignificantBits();

        Doctor doctor = new Doctor(docId,dto.getName(),dto.getAddress(),dto.getContact(),dto.getSalary());

        doctorRepo.save(doctor);
    }

    @Override
    public ResponseDoctorDto getDoctor(long id) {
        Optional<Doctor> selectedDoctor = doctorRepo.findById(id);
        if(selectedDoctor.isEmpty()){
            throw new EntryNotFoundException("Doctor Not Found");
        }
        return doctorMapper.toResponseDoctorDto(selectedDoctor.get());
    }

    /*@Override
    public List<ResponseDoctorDto> findDoctorByName(String name) {
        List<Doctor> allByName = doctorRepo.findAllByName(name);
        return null;
    }*/

    @Override
    public void deleteDoctor(long id) {
        Optional<Doctor> selectedDoctor = doctorRepo.findById(id);
        if(selectedDoctor.isEmpty()){
            throw new EntryNotFoundException("Doctor Not Found");
        }
        doctorRepo.deleteById(selectedDoctor.get().getId());
    }

    @Override
    public void updateDoctor(long id, RequestDoctorDto dto) {
        Optional<Doctor> selectedDoctor = doctorRepo.findById(id);
        if(selectedDoctor.isEmpty()){
            throw new EntryNotFoundException("Doctor Not Found");
        }
        Doctor doc = selectedDoctor.get();
        doc.setName(dto.getName());
        doc.setAddress(dto.getAddress());
        doc.setContact(dto.getContact());
        doc.setSalary(dto.getSalary());
        doctorRepo.save(doc);
    }

    @Override
    public PaginatedDoctorResponseDto getAllDoctors(String searchText, int page, int size) {
        searchText = "%"+searchText+"%";
        List<Doctor> doctors = doctorRepo.searchDoctors(searchText, PageRequest.of(page, size));
        long doctorCount = doctorRepo.countDoctors(searchText);
        List<ResponseDoctorDto> dtos = doctorMapper.toResponseDoctorDtoList(doctors);
        /*doctors.forEach(doc->{
            dtos.add(
                    new ResponseDoctorDto(
                            doc.getId(), doc.getName(),
                            doc.getAddress(), doc.getContact(), doc.getSalary()
                    )
            );
        });*/
        return new PaginatedDoctorResponseDto( doctorCount, dtos );
    }
}
