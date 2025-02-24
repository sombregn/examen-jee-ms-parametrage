package com.isi.dev.tpparametrage.student;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmailPerso(String emailPerso);
    Optional<Student> findByEmailPro(String emailPro);
    Optional<Student> findByPhoneNumber(String phoneNumber);
    Optional<Student> findByRegistrationNu(String registrationNu);
}
