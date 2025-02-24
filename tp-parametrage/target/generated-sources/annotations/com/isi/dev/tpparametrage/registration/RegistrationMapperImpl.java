package com.isi.dev.tpparametrage.registration;

import com.isi.dev.tpparametrage.academicyear.AcademieYear;
import com.isi.dev.tpparametrage.classe.Classe;
import com.isi.dev.tpparametrage.program.Program;
import com.isi.dev.tpparametrage.registration.Registration.RegistrationBuilder;
import com.isi.dev.tpparametrage.student.Student;
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
public class RegistrationMapperImpl implements RegistrationMapper {

    @Override
    public Registration toRegistration(RegistrationRequest request) {
        if ( request == null ) {
            return null;
        }

        RegistrationBuilder registration = Registration.builder();

        registration.student( RegistrationMapper.mapStudentIdToStudent( request.getStudentId() ) );
        registration.classe( RegistrationMapper.mapClasseIdToClasse( request.getClasseId() ) );
        registration.program( RegistrationMapper.mapProgramIdToProgram( request.getProgramId() ) );
        registration.academieYear( RegistrationMapper.mapAcademieYearIdToAcademieYear( request.getAcademieYearId() ) );
        registration.id( request.getId() );
        registration.date( request.getDate() );
        registration.description( request.getDescription() );
        registration.archive( request.getArchive() );

        return registration.build();
    }

    @Override
    public RegistrationResponse toRegistrationResponse(Registration registration) {
        if ( registration == null ) {
            return null;
        }

        RegistrationResponse registrationResponse = new RegistrationResponse();

        registrationResponse.setStudentId( registrationStudentId( registration ) );
        registrationResponse.setClasseId( registrationClasseId( registration ) );
        registrationResponse.setProgramId( registrationProgramId( registration ) );
        registrationResponse.setAcademieYearId( registrationAcademieYearId( registration ) );
        registrationResponse.setStudentFistName( registrationStudentFirstName( registration ) );
        registrationResponse.setStudentLastName( registrationStudentLastName( registration ) );
        registrationResponse.setRegistrationNu( registrationStudentRegistrationNu( registration ) );
        registrationResponse.setProgramName( registrationProgramName( registration ) );
        registrationResponse.setClasseName( registrationClasseName( registration ) );
        registrationResponse.setAcademieName( registrationAcademieYearName( registration ) );
        registrationResponse.setId( registration.getId() );
        registrationResponse.setDate( registration.getDate() );
        registrationResponse.setDescription( registration.getDescription() );
        registrationResponse.setArchive( registration.getArchive() );

        return registrationResponse;
    }

    @Override
    public List<RegistrationResponse> toRegistrationResponseList(List<Registration> registrations) {
        if ( registrations == null ) {
            return null;
        }

        List<RegistrationResponse> list = new ArrayList<RegistrationResponse>( registrations.size() );
        for ( Registration registration : registrations ) {
            list.add( toRegistrationResponse( registration ) );
        }

        return list;
    }

    private Long registrationStudentId(Registration registration) {
        if ( registration == null ) {
            return null;
        }
        Student student = registration.getStudent();
        if ( student == null ) {
            return null;
        }
        Long id = student.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long registrationClasseId(Registration registration) {
        if ( registration == null ) {
            return null;
        }
        Classe classe = registration.getClasse();
        if ( classe == null ) {
            return null;
        }
        Long id = classe.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long registrationProgramId(Registration registration) {
        if ( registration == null ) {
            return null;
        }
        Program program = registration.getProgram();
        if ( program == null ) {
            return null;
        }
        Long id = program.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long registrationAcademieYearId(Registration registration) {
        if ( registration == null ) {
            return null;
        }
        AcademieYear academieYear = registration.getAcademieYear();
        if ( academieYear == null ) {
            return null;
        }
        Long id = academieYear.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String registrationStudentFirstName(Registration registration) {
        if ( registration == null ) {
            return null;
        }
        Student student = registration.getStudent();
        if ( student == null ) {
            return null;
        }
        String firstName = student.getFirstName();
        if ( firstName == null ) {
            return null;
        }
        return firstName;
    }

    private String registrationStudentLastName(Registration registration) {
        if ( registration == null ) {
            return null;
        }
        Student student = registration.getStudent();
        if ( student == null ) {
            return null;
        }
        String lastName = student.getLastName();
        if ( lastName == null ) {
            return null;
        }
        return lastName;
    }

    private String registrationStudentRegistrationNu(Registration registration) {
        if ( registration == null ) {
            return null;
        }
        Student student = registration.getStudent();
        if ( student == null ) {
            return null;
        }
        String registrationNu = student.getRegistrationNu();
        if ( registrationNu == null ) {
            return null;
        }
        return registrationNu;
    }

    private String registrationProgramName(Registration registration) {
        if ( registration == null ) {
            return null;
        }
        Program program = registration.getProgram();
        if ( program == null ) {
            return null;
        }
        String name = program.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String registrationClasseName(Registration registration) {
        if ( registration == null ) {
            return null;
        }
        Classe classe = registration.getClasse();
        if ( classe == null ) {
            return null;
        }
        String name = classe.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String registrationAcademieYearName(Registration registration) {
        if ( registration == null ) {
            return null;
        }
        AcademieYear academieYear = registration.getAcademieYear();
        if ( academieYear == null ) {
            return null;
        }
        String name = academieYear.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
