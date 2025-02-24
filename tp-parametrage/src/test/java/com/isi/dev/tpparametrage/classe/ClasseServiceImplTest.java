package com.isi.dev.tpparametrage.classe;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.isi.dev.tpparametrage.exception.EntityExistsException;
import com.isi.dev.tpparametrage.exception.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ClasseServiceImplTest {

    @Mock
    private ClasseRepository classeRepository;

    @Mock
    private ClasseMapper classeMapper;

    @Mock
    private MessageSource messageSource;

    @InjectMocks
    private ClasseServiceImpl classeService;

    private ClasseRequest classeRequest;
    private Classe classe;
    private ClasseResponse classeResponse;

    @BeforeEach
    void setUp() {
        classeRequest = new ClasseRequest();
        classeRequest.setId(1L);
        classeRequest.setName("Test Classe");

        classe = new Classe();
        classe.setId(1L);
        classe.setName("Test Classe");

        classeResponse = new ClasseResponse();
        classeResponse.setId(1L);
        classeResponse.setName("Test Classe");
    }

    @Test
    void testAddClasse_Success() {
        when(classeRepository.findByName(classeRequest.getName())).thenReturn(Optional.empty());
        when(classeMapper.toClasse(classeRequest)).thenReturn(classe);
        when(classeRepository.save(classe)).thenReturn(classe);
        when(classeMapper.toClasseResponse(classe)).thenReturn(classeResponse);

        ClasseResponse response = classeService.addClasse(classeRequest);

        assertNotNull(response);
        assertEquals("Test Classe", response.getName());
        verify(classeRepository).save(classe);
    }

    @Test
    void testAddClasse_ThrowsEntityExistsException() {
        when(classeRepository.findByName(classeRequest.getName())).thenReturn(Optional.of(classe));
        when(messageSource.getMessage(anyString(), any(), any(Locale.class))).thenReturn("Classe already exists");

        assertThrows(EntityExistsException.class, () -> classeService.addClasse(classeRequest));
    }

    @Test
    void testGetClasseById_Success() {
        when(classeRepository.findById(1L)).thenReturn(Optional.of(classe));
        when(classeMapper.toClasseResponse(classe)).thenReturn(classeResponse);

        ClasseResponse response = classeService.getClasseById(1L);

        assertNotNull(response);
        assertEquals(1L, response.getId());
    }

    @Test
    void testGetClasseById_ThrowsEntityNotFoundException() {
        when(classeRepository.findById(1L)).thenReturn(Optional.empty());
        when(messageSource.getMessage(anyString(), any(), any(Locale.class))).thenReturn("Classe not found");

        assertThrows(EntityNotFoundException.class, () -> classeService.getClasseById(1L));
    }

    @Test
    void testGetAllClasses() {
        when(classeRepository.findAll()).thenReturn(List.of(classe));
        when(classeMapper.toClasseResponseList(List.of(classe))).thenReturn(List.of(classeResponse));

        List<ClasseResponse> responses = classeService.getAllClasses();

        assertFalse(responses.isEmpty());
        assertEquals(1, responses.size());
    }

    @Test
    void testUpdateClasse_Success() {
        when(classeRepository.findById(classeRequest.getId())).thenReturn(Optional.of(classe));
        when(classeRepository.save(any(Classe.class))).thenReturn(classe);
        when(classeMapper.toClasseResponse(classe)).thenReturn(classeResponse);

        ClasseResponse response = classeService.updateClasse(classeRequest);

        assertNotNull(response);
        assertEquals("Test Classe", response.getName());
    }

    @Test
    void testUpdateClasse_ThrowsEntityNotFoundException() {
        when(classeRepository.findById(classeRequest.getId())).thenReturn(Optional.empty());
        when(messageSource.getMessage(anyString(), any(), any(Locale.class))).thenReturn("Classe not found");

        assertThrows(EntityNotFoundException.class, () -> classeService.updateClasse(classeRequest));
    }

    @Test
    void testDeleteClasseById_Success() {
        when(classeRepository.findById(1L)).thenReturn(Optional.of(classe));

        classeService.deleteClasseById(1L);

        verify(classeRepository).delete(classe);
    }

    @Test
    void testDeleteClasseById_ThrowsEntityNotFoundException() {
        when(classeRepository.findById(1L)).thenReturn(Optional.empty());
        when(messageSource.getMessage(anyString(), any(), any(Locale.class))).thenReturn("Classe not found");

        assertThrows(EntityNotFoundException.class, () -> classeService.deleteClasseById(1L));
    }
}
