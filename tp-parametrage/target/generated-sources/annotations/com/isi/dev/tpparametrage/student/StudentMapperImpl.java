package com.isi.dev.tpparametrage.student;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-23T16:48:13+0000",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.12 (OpenLogic)"
)
@Component
public class StudentMapperImpl implements StudentMapper {

    @Override
    public Student toStudent(StudentRequest studentRequest) {
        if ( studentRequest == null ) {
            return null;
        }

        Student student = new Student();

        student.setId( studentRequest.getId() );
        student.setFirstName( studentRequest.getFirstName() );
        student.setLastName( studentRequest.getLastName() );
        student.setEmailPro( studentRequest.getEmailPro() );
        student.setEmailPerso( studentRequest.getEmailPerso() );
        student.setPhoneNumber( studentRequest.getPhoneNumber() );
        student.setAddress( studentRequest.getAddress() );
        student.setArchive( studentRequest.getArchive() );
        student.setRegistrationNu( studentRequest.getRegistrationNu() );

        return student;
    }

    @Override
    public StudentResponse toStudentResponse(Student student) {
        if ( student == null ) {
            return null;
        }

        StudentResponse studentResponse = new StudentResponse();

        studentResponse.setId( student.getId() );
        studentResponse.setFirstName( student.getFirstName() );
        studentResponse.setLastName( student.getLastName() );
        studentResponse.setEmailPro( student.getEmailPro() );
        studentResponse.setEmailPerso( student.getEmailPerso() );
        studentResponse.setPhoneNumber( student.getPhoneNumber() );
        studentResponse.setAddress( student.getAddress() );
        studentResponse.setArchive( student.getArchive() );
        studentResponse.setRegistrationNu( student.getRegistrationNu() );

        return studentResponse;
    }

    @Override
    public List<StudentResponse> toStudentDtoResponseList(List<Student> studentList) {
        if ( studentList == null ) {
            return null;
        }

        List<StudentResponse> list = new ArrayList<StudentResponse>( studentList.size() );
        for ( Student student : studentList ) {
            list.add( toStudentResponse( student ) );
        }

        return list;
    }
}
