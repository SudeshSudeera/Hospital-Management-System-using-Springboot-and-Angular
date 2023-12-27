package com.ssacademy.healthcare.system.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Doctor {
    @Id
    private long id;
    private String name;
    private String address;
    private String contact;
    private double salary;
}
