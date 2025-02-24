package com.isi.dev.tpparametrage.student;

import com.isi.dev.tpparametrage.exception.EntityExistsException;
import com.isi.dev.tpparametrage.exception.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;

import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceImpTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private MessageSource messageSource;

    @Mock
    private StudentMapper studentMapper;

    @InjectMocks
    private StudentServiceImp studentService;

    private Student student;
    private StudentRequest studentRequest;
    private StudentResponse studentResponse;

    @BeforeEach
    void setUp() {
        student = new Student();
        student.setId(1L);
        student.setEmailPerso("test@example.com");
        student.setEmailPro("pro@example.com");
        student.setPhoneNumber("123456789");
        student.setRegistrationNu("REG123");

        studentRequest = new StudentRequest();
        studentRequest.setId(1L);
        studentRequest.setEmailPerso("test@example.com");
        studentRequest.setEmailPro("pro@example.com");
        studentRequest.setPhoneNumber("123456789");
        studentRequest.setRegistrationNu("REG123");

        studentResponse = new StudentResponse();
        studentResponse.setId(1L);
        studentResponse.setEmailPerso("test@example.com");
        studentResponse.setEmailPro("pro@example.com");
    }

    @Test
    void testSaveStudent_Success() {
        when(studentRepository.findByEmailPerso(studentRequest.getEmailPerso())).thenReturn(Optional.empty());
        when(studentRepository.findByEmailPro(studentRequest.getEmailPro())).thenReturn(Optional.empty());
        when(studentRepository.findByPhoneNumber(studentRequest.getPhoneNumber())).thenReturn(Optional.empty());
        when(studentRepository.findByRegistrationNu(studentRequest.getRegistrationNu())).thenReturn(Optional.empty());

        when(studentMapper.toStudent(studentRequest)).thenReturn(student);
        when(studentRepository.save(student)).thenReturn(student);
        when(studentMapper.toStudentResponse(student)).thenReturn(studentResponse);

        StudentResponse result = studentService.saveStudent(studentRequest);

        assertNotNull(result);
        assertEquals("test@example.com", result.getEmailPerso());
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void testSaveStudent_EmailPersoExists_ShouldThrowException() {
        when(studentRepository.findByEmailPerso(studentRequest.getEmailPerso())).thenReturn(Optional.of(student));
        when(messageSource.getMessage(eq("emailPerso.exist"), any(), eq(Locale.getDefault()))).thenReturn("Email déjà utilisé");

        EntityExistsException exception = assertThrows(EntityExistsException.class, () -> studentService.saveStudent(studentRequest));
        assertEquals("Email déjà utilisé", exception.getMessage());
    }

    @Test
    void testGetStudentById_Success() {
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(studentMapper.toStudentResponse(student)).thenReturn(studentResponse);

        StudentResponse result = studentService.getStudentById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void testGetStudentById_NotFound_ShouldThrowException() {
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());
        when(messageSource.getMessage(eq("student.notfound"), any(), eq(Locale.getDefault()))).thenReturn("Étudiant non trouvé");

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> studentService.getStudentById(1L));
        assertEquals("Étudiant non trouvé", exception.getMessage());
    }

    @Test
    void testDeleteStudent_Success() {
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        studentService.deleteStudent(1L);

        verify(studentRepository, times(1)).delete(student);
    }

    @Test
    void testDeleteStudent_NotFound_ShouldThrowException() {
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());
        when(messageSource.getMessage(eq("student.notfound"), any(), eq(Locale.getDefault()))).thenReturn("Étudiant non trouvé");

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> studentService.deleteStudent(1L));
        assertEquals("Étudiant non trouvé", exception.getMessage());
    }
}
