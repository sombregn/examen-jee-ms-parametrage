package com.isi.dev.tpparametrage.student;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {
    Student toStudent(StudentRequest studentRequest);
    StudentResponse toStudentResponse(Student student);
    List<StudentResponse> toStudentDtoResponseList(List<Student> studentList) ;
}
