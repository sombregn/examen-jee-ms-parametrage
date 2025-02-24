package com.isi.dev.tpparametrage.student;

import com.isi.dev.tpparametrage.exception.EntityExistsException;
import com.isi.dev.tpparametrage.exception.EntityNotFoundException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Getter
@Setter
@Service
public class StudentServiceImp implements StudentService {

    private final StudentRepository studentRepository;
    private final MessageSource messageSource;
    private final StudentMapper studentMapper;

    public StudentServiceImp(StudentRepository studentRepository, MessageSource messageSource, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.messageSource = messageSource;
        this.studentMapper = studentMapper;
    }

    @Override
    public StudentResponse saveStudent(StudentRequest studentRequest) {
        if (studentRepository.findByEmailPerso(studentRequest.getEmailPerso()).isPresent()) {
            throw new EntityExistsException(messageSource.getMessage("emailPerso.exist", new Object[]{studentRequest.getEmailPerso()}, Locale.getDefault()));
        }
        if (studentRepository.findByEmailPro(studentRequest.getEmailPro()).isPresent()) {
            throw new EntityExistsException(messageSource.getMessage("emailPro.exist", new Object[]{studentRequest.getEmailPro()}, Locale.getDefault()));
        }
        if (studentRepository.findByPhoneNumber(studentRequest.getPhoneNumber()).isPresent()) {
            throw new EntityExistsException(messageSource.getMessage("phoneNumber.exist", new Object[]{studentRequest.getPhoneNumber()}, Locale.getDefault()));
        }
        if (studentRepository.findByRegistrationNu(studentRequest.getRegistrationNu()).isPresent()) {
            throw new EntityExistsException(messageSource.getMessage("registrationNu.exist", new Object[]{studentRequest.getRegistrationNu()}, Locale.getDefault()));
        }
        Student student = studentMapper.toStudent(studentRequest);
        var saveStudent = studentRepository.save(student);
        return studentMapper.toStudentResponse(saveStudent);
    }

    @Override
    public List<StudentResponse> getAllStudents() {
        return studentMapper.toStudentDtoResponseList(studentRepository.findAll());
    }

    @Override
    public StudentResponse getStudentById(Long id) {
        return studentRepository.findById(id)
                .map(studentMapper::toStudentResponse)
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("student.notfound", new Object[]{id}, Locale.getDefault())));
    }

//    @Override
//    public StudentResponse updateStudent(StudentRequest studentRequest) {
//
//        studentRepository.findByEmailPerso(studentRequest.getEmailPerso())
//                .ifPresent(existingEmailPerso -> {
//                    if (!existingEmailPerso.getId().equals(studentRequest.getId())) {
//                        throw new EntityExistsException(messageSource.getMessage("emailPerso.exist", new Object[]{existingEmailPerso}, Locale.getDefault() ));
//                    }});
//        studentRepository.findByEmailPro(studentRequest.getEmailPro())
//                .ifPresent(existingEmailPro -> {
//                    if (!existingEmailPro.getId().equals(studentRequest.getId())) {
//                        throw new EntityExistsException(messageSource.getMessage("emailPro.exist", new Object[]{existingEmailPro}, Locale.getDefault() ));
//                    }});
//        studentRepository.findByPhoneNumber(studentRequest.getPhoneNumber())
//                .ifPresent(existingPhoneNumber -> {
//                    if (!existingPhoneNumber.getId().equals(studentRequest.getId())) {
//                        throw new EntityExistsException(messageSource.getMessage("phoneNumber.exist", new Object[]{existingPhoneNumber}, Locale.getDefault() ));
//                    }});
//        studentRepository.findByRegistrationNu(studentRequest.getRegistrationNu())
//                .ifPresent(existingRegistrationNu -> {
//                    if (!existingRegistrationNu.getId().equals(studentRequest.getId())) {
//                        throw new EntityExistsException(messageSource.getMessage("registrationNu.exist", new Object[]{existingRegistrationNu}, Locale.getDefault() ));
//                    }});
//
//        return studentRepository.findById(id)
//                .map(student -> {
//                    student.setFirstName(studentRequest.getFirstName());
//                    student.setLastName(studentRequest.getLastName());
//                    student.setEmailPerso(studentRequest.getEmailPerso());
//                    student.setEmailPro(studentRequest.getEmailPro());
//                    student.setPhoneNumber(studentRequest.getPhoneNumber());
//                    student.setAddress(studentRequest.getAddress());
//                    student.setArchive(studentRequest.getArchive());
//                    student.setRegistrationNu(studentRequest.getRegistrationNu());
//                    var updateStudent = studentRepository.save(student);
//                    return studentMapper.toStudentResponse(updateStudent);
//                }).orElseThrow( () ->  new EntityNotFoundException (
//                        messageSource.getMessage("student.notfound", new Object[]{}, Locale.getDefault())
//                ));
//    }


    @Override
    public StudentResponse updateStudent(StudentRequest request) {
        studentRepository.findByRegistrationNu(request.getRegistrationNu())
                .ifPresent(existingNum -> {
                    if (!existingNum.getId().equals(request.getId())) {
                        throw new EntityExistsException(messageSource.getMessage("registrationNu.exist", new Object[]{existingNum}, Locale.getDefault() ));
                    }
                });
        studentRepository.findByEmailPerso(request.getEmailPerso())
                .ifPresent(existingEmail -> {
                    if (!existingEmail.getId().equals(request.getId())) {
                        throw new EntityExistsException(messageSource.getMessage("emailPerso.exist", new Object[]{request}, Locale.getDefault() ));
                    }
                });
        studentRepository.findByEmailPro(request.getEmailPro())
                .ifPresent(existingEmail -> {
                    if (!existingEmail.getId().equals(request.getId())) {
                        throw new EntityExistsException(messageSource.getMessage("emailPro.exist", new Object[]{existingEmail}, Locale.getDefault() ));
                    }
                });
        studentRepository.findByPhoneNumber(request.getPhoneNumber())
                .ifPresent(existingPhone -> {
                    if (!existingPhone.getId().equals(request.getId())) {
                        throw new EntityExistsException(messageSource.getMessage("phoneNumber.exist", new Object[]{existingPhone}, Locale.getDefault() ));
                    }
                });
        var student = studentRepository.findById(request.getId())
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("student.notfound", new  Object[]{""}, Locale.getDefault() )));
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setEmailPro(request.getEmailPro());
        student.setEmailPerso(request.getEmailPerso());
        student.setPhoneNumber(request.getPhoneNumber());
        student.setAddress(request.getAddress());
        student.setArchive(request.getArchive());
        student.setRegistrationNu(request.getRegistrationNu());
        var updatedStudent = studentRepository.save(student);
        return studentMapper.toStudentResponse(updatedStudent);
    }

    @Override
    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException (messageSource.getMessage("student.notfound", new Object[]{id}, Locale.getDefault())) );
            studentRepository.delete(student);
    }
}
