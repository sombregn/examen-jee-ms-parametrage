package com.isi.dev.tpparametrage.registration;

import com.isi.dev.tpparametrage.academicyear.AcademieYearRepository;
import com.isi.dev.tpparametrage.classe.ClasseRepository;
import com.isi.dev.tpparametrage.exception.EntityExistsException;
import com.isi.dev.tpparametrage.exception.EntityNotFoundException;
import com.isi.dev.tpparametrage.program.ProgramRepository;
import com.isi.dev.tpparametrage.student.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
@AllArgsConstructor
@Getter
@Setter
public class RegistrationServiceImpl implements RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final RegistrationMapper registrationMapper;
    private final StudentRepository studentRepository;
//    private final AdministrativeAgentRepository administrativeAgentRepository;
    private final ClasseRepository classeRepository;
    private final ProgramRepository programRepository;
    private final AcademieYearRepository academieYearRepository;
    private final MessageSource messageSource;

    @Override
    public RegistrationResponse addRegistration(RegistrationRequest request) {

        var student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("student.notfound", new Object[]{request.getStudentId()}, Locale.getDefault())));

        var classe = classeRepository.findById(request.getClasseId())
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("classe.notfound", new Object[]{request.getClasseId()}, Locale.getDefault())));

        var program = programRepository.findById(request.getProgramId())
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("program.notfound", new Object[]{request.getProgramId()}, Locale.getDefault())));

        var academieYear = academieYearRepository.findById(request.getAcademieYearId())
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("academieYear.notfound", new Object[]{request.getAcademieYearId()}, Locale.getDefault())));


        if (registrationRepository.findByStudentIdAndClasseIdAndProgramIdAndAcademieYearId(
                request.getStudentId(),
                request.getClasseId(),
                request.getProgramId(),
                request.getAcademieYearId()).isPresent()){
            throw new EntityExistsException(messageSource.getMessage("student.classe.program.academieYear.exists",
                    new Object[]{
                            request.getStudentId(),
                            request.getClasseId(),
                            request.getProgramId(),
                            request.getAcademieYearId()
                    },
                    Locale.getDefault()));
        }

        Registration registration = registrationMapper.toRegistration(request);
        registration.setStudent(student);
        registration.setProgram(program);
        registration.setAcademieYear(academieYear);
        registration.setClasse(classe);

        var savedRegistration = registrationRepository.save(registration);
        return registrationMapper.toRegistrationResponse(savedRegistration);
    }

    @Override
    public RegistrationResponse getRegistrationById(Long id) {
        return registrationRepository.findById(id)
                .map(registrationMapper::toRegistrationResponse)
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("registration.notfound", new Object[]{id}, Locale.getDefault())));
    }

    @Override
    public List<RegistrationResponse> getAllRegistrations() {
        return registrationMapper.toRegistrationResponseList(registrationRepository.findAll());
    }

    @Override
    public RegistrationResponse updateRegistration(RegistrationRequest request) {
        var classe = classeRepository.findById(request.getClasseId())
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("classe.notfound", new Object[]{request.getClasseId()}, Locale.getDefault())));
        var student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("student.notfound", new Object[]{request.getStudentId()}, Locale.getDefault())));
        var program = programRepository.findById(request.getProgramId())
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("program.notfound", new Object[]{request.getProgramId()}, Locale.getDefault())));
        var academieYear = academieYearRepository.findById(request.getAcademieYearId())
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("academieYear.notfound", new Object[]{request.getAcademieYearId()}, Locale.getDefault())));

        var existingRegistration = registrationRepository.findByStudentIdAndClasseIdAndProgramIdAndAcademieYearId(
                request.getStudentId(),
                request.getClasseId(),
                request.getProgramId(),
                request.getAcademieYearId()
        );

        if (existingRegistration.isPresent() && !existingRegistration.get().getId().equals(request.getId())) {
            throw new EntityExistsException(messageSource.getMessage("student.classe.program.academieYear.exists",
                    new Object[]{
                            request.getStudentId(),
                            request.getClasseId(),
                            request.getProgramId(),
                            request.getAcademieYearId()
                    }, Locale.getDefault()));
        }

        var registration = registrationRepository.findById(request.getId())
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("registration.notfound", new Object[]{request.getId()}, Locale.getDefault())));

        registration.setStudent(student);
        registration.setProgram(program);
        registration.setAcademieYear(academieYear);
        registration.setClasse(classe);
        registration.setDate(request.getDate());
        registration.setArchive(request.getArchive());
        registration.setDescription(request.getDescription());

        var updateRegistration = registrationRepository.save(registration);
        return registrationMapper.toRegistrationResponse(updateRegistration);
    }

    @Override
    public void deleteRegistrationById(Long id) {
        Registration registration = registrationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("registration.notfound", new Object[]{id}, Locale.getDefault())));
        registrationRepository.delete(registration);
    }

}

