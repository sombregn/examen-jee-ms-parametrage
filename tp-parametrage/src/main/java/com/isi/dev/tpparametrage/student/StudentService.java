package com.isi.dev.tpparametrage.student;

import java.util.List;

public interface StudentService {
    StudentResponse saveStudent(StudentRequest studentRequest);
    List<StudentResponse> getAllStudents();
    StudentResponse getStudentById(Long id);
    StudentResponse updateStudent(StudentRequest studentRequest);
    void deleteStudent(Long id);
}
