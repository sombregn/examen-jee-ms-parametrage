package com.isi.dev.tpparametrage.registration;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.isi.dev.tpparametrage.academicyear.AcademieYear;
import com.isi.dev.tpparametrage.classe.Classe;
import com.isi.dev.tpparametrage.exception.EntityExistsException;
import com.isi.dev.tpparametrage.exception.EntityNotFoundException;
import com.isi.dev.tpparametrage.program.Program;
import com.isi.dev.tpparametrage.academicyear.AcademieYearRepository;
import com.isi.dev.tpparametrage.classe.ClasseRepository;
import com.isi.dev.tpparametrage.program.ProgramRepository;
import com.isi.dev.tpparametrage.student.Student;
import com.isi.dev.tpparametrage.student.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceImplTest {

    @Mock
    private RegistrationRepository registrationRepository;

    @Mock
    private RegistrationMapper registrationMapper;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private ClasseRepository classeRepository;

    @Mock
    private ProgramRepository programRepository;

    @Mock
    private AcademieYearRepository academieYearRepository;

    @Mock
    private MessageSource messageSource;

    @InjectMocks
    private RegistrationServiceImpl registrationService;

    private RegistrationRequest registrationRequest;
    private Registration registration;
    private RegistrationResponse registrationResponse;

    @BeforeEach
    void setUp() {
        registrationRequest = new RegistrationRequest();
        registrationRequest.setId(1L);
        registrationRequest.setStudentId(1L);
        registrationRequest.setClasseId(1L);
        registrationRequest.setProgramId(1L);
        registrationRequest.setAcademieYearId(1L);

        registration = new Registration();
        registration.setId(1L);

        registrationResponse = new RegistrationResponse();
        registrationResponse.setId(1L);
    }

    @Test
    void testAddRegistration_Success() {
        when(studentRepository.findById(1L)).thenReturn(Optional.of(new Student()));
        when(classeRepository.findById(1L)).thenReturn(Optional.of(new Classe()));
        when(programRepository.findById(1L)).thenReturn(Optional.of(new Program()));
        when(academieYearRepository.findById(1L)).thenReturn(Optional.of(new AcademieYear()));
        when(registrationRepository.findByStudentIdAndClasseIdAndProgramIdAndAcademieYearId(1L, 1L, 1L, 1L))
                .thenReturn(Optional.empty());
        when(registrationMapper.toRegistration(registrationRequest)).thenReturn(registration);
        when(registrationRepository.save(registration)).thenReturn(registration);
        when(registrationMapper.toRegistrationResponse(registration)).thenReturn(registrationResponse);

        RegistrationResponse response = registrationService.addRegistration(registrationRequest);

        assertNotNull(response);
        assertEquals(1L, response.getId());
    }

    @Test
    void testAddRegistration_ThrowsEntityExistsException() {
        // Given
        RegistrationRequest request = new RegistrationRequest(
                1L, // id
                LocalDate.now(), // date
                "Test description", // description
                false, // archive
                1L, // studentId
                1L, // classeId
                1L, // programId
                1L  // academieYearId
        );
        Student student = new Student();
        Classe classe = new Classe();
        Program program = new Program();
        AcademieYear academieYear = new AcademieYear();
        Registration existingRegistration = new Registration();

        // Configure les stubs pour retourner les objets nécessaires
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(classeRepository.findById(1L)).thenReturn(Optional.of(classe));
        when(programRepository.findById(1L)).thenReturn(Optional.of(program));
        when(academieYearRepository.findById(1L)).thenReturn(Optional.of(academieYear));
        when(registrationRepository.findByStudentIdAndClasseIdAndProgramIdAndAcademieYearId(1L, 1L, 1L, 1L))
                .thenReturn(Optional.of(existingRegistration));

        // When & Then
        assertThrows(EntityExistsException.class, () -> registrationService.addRegistration(request));

        // Vérifie que la méthode save n'est jamais appelée
        verify(registrationRepository, never()).save(any(Registration.class));
    }


    @Test
    void testGetRegistrationById_Success() {
        when(registrationRepository.findById(1L)).thenReturn(Optional.of(registration));
        when(registrationMapper.toRegistrationResponse(registration)).thenReturn(registrationResponse);

        RegistrationResponse response = registrationService.getRegistrationById(1L);

        assertNotNull(response);
        assertEquals(1L, response.getId());
    }

    @Test
    void testGetRegistrationById_ThrowsEntityNotFoundException() {
        when(registrationRepository.findById(1L)).thenReturn(Optional.empty());
        when(messageSource.getMessage(anyString(), any(), any(Locale.class))).thenReturn("Registration not found");

        assertThrows(EntityNotFoundException.class, () -> registrationService.getRegistrationById(1L));
    }

    @Test
    void testGetAllRegistrations() {
        when(registrationRepository.findAll()).thenReturn(List.of(registration));
        when(registrationMapper.toRegistrationResponseList(List.of(registration))).thenReturn(List.of(registrationResponse));

        List<RegistrationResponse> responses = registrationService.getAllRegistrations();

        assertFalse(responses.isEmpty());
        assertEquals(1, responses.size());
    }

    @Test
    void testDeleteRegistrationById_Success() {
        when(registrationRepository.findById(1L)).thenReturn(Optional.of(registration));

        registrationService.deleteRegistrationById(1L);

        verify(registrationRepository).delete(registration);
    }

    @Test
    void testDeleteRegistrationById_ThrowsEntityNotFoundException() {
        when(registrationRepository.findById(1L)).thenReturn(Optional.empty());
        when(messageSource.getMessage(anyString(), any(), any(Locale.class))).thenReturn("Registration not found");

        assertThrows(EntityNotFoundException.class, () -> registrationService.deleteRegistrationById(1L));
    }
}
