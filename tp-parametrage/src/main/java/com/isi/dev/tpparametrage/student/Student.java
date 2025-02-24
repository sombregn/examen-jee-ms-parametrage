package com.isi.dev.tpparametrage.student;

import com.isi.dev.tpparametrage.registration.Registration;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "student_tb")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Student  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String emailPro;
    private String emailPerso;
    private String phoneNumber;
    private String address;
    private Boolean archive = false;
    private String registrationNu;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Registration> registrations;
}
