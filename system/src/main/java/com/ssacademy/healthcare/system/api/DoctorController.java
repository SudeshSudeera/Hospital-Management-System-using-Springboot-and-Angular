package com.ssacademy.healthcare.system.api;

import com.ssacademy.healthcare.system.dto.request.RequestDoctorDto;
import com.ssacademy.healthcare.system.service.DoctorService;
import com.ssacademy.healthcare.system.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {
    private final DoctorService doctorService;
    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<StandardResponse> createDoctor(@RequestBody RequestDoctorDto doctorDto){
        doctorService.createDoctor(doctorDto);
        return new ResponseEntity<>(
                new StandardResponse(201,"Doctor was saved",doctorDto.getName()),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> findDoctor(@PathVariable long id){
        return new ResponseEntity<>(
                new StandardResponse(200,"Doctor data",doctorService.getDoctor(id)),
                HttpStatus.OK
        );
    }

    @PutMapping(params = "id")
    public ResponseEntity<StandardResponse> updateDoctor(
            @RequestParam long id,
            @RequestBody RequestDoctorDto doctorDto
    ){
        doctorService.updateDoctor(id, doctorDto);
        return new ResponseEntity<>(
                new StandardResponse(201,"Update data",doctorDto.getName()),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StandardResponse> deleteDoctor(@PathVariable long id){
        doctorService.deleteDoctor(id);
        return new ResponseEntity<>(
                new StandardResponse(204,"Deleted data",id),
                HttpStatus.NO_CONTENT
        );
    }

    @GetMapping(path = "/list", params = {"searchText", "page", "size"})
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DOCTOR')")
    public ResponseEntity<StandardResponse> findAllDoctors(
            @RequestParam String searchText,
            @RequestParam int page,
            @RequestParam int size
    ){
        return new ResponseEntity<>(
                new StandardResponse(200,"Data list",doctorService.getAllDoctors(
                        searchText, page, size
                )),
                HttpStatus.OK
        );
    }
}
