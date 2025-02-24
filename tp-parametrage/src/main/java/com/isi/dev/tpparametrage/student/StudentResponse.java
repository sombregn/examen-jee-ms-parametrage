package com.isi.dev.tpparametrage.student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String emailPro;
    private String emailPerso;
    private String phoneNumber;
    private String address;
    private Boolean archive;
    private String registrationNu;
}
