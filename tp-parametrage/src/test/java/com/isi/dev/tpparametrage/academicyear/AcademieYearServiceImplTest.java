package com.isi.dev.tpparametrage.academicyear;

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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AcademieYearServiceImplTest {

    @Mock
    private AcademieYearRepository academieYearRepository;

    @Mock
    private AcademieYearMapper academieYearMapper;

    @Mock
    private MessageSource messageSource;

    @InjectMocks
    private AcademieYearServiceImpl academieYearService;

    private AcademieYear academieYear;
    private AcademieYearRequest academieYearRequest;
    private AcademieYearResponse academieYearResponse;

    @BeforeEach
    void setUp() {
        academieYear = new AcademieYear(1L, "2023-2024", "Academic Year", false);
        academieYearRequest = new AcademieYearRequest(1L, "2023-2024", "Academic Year", false);
        academieYearResponse = new AcademieYearResponse(1L, "2023-2024", "Academic Year", false);
    }

    @Test
    void testAddAcademieYear_Success() {
        when(academieYearRepository.findByName(academieYearRequest.getName())).thenReturn(Optional.empty());
        when(academieYearMapper.toAcademieYear(academieYearRequest)).thenReturn(academieYear);
        when(academieYearRepository.save(academieYear)).thenReturn(academieYear);
        when(academieYearMapper.toAcademieYearResponse(academieYear)).thenReturn(academieYearResponse);

        AcademieYearResponse response = academieYearService.addAcademieYear(academieYearRequest);

        assertNotNull(response);
        assertEquals(academieYearResponse.getName(), response.getName());
        verify(academieYearRepository, times(1)).save(academieYear);
    }

    @Test
    void testAddAcademieYear_AlreadyExists() {
        when(academieYearRepository.findByName(academieYearRequest.getName())).thenReturn(Optional.of(academieYear));
        when(messageSource.getMessage(any(), any(), any(Locale.class))).thenReturn("Academic year already exists");

        assertThrows(EntityExistsException.class, () -> academieYearService.addAcademieYear(academieYearRequest));
    }

    @Test
    void testGetAcademieYearById_Success() {
        when(academieYearRepository.findById(1L)).thenReturn(Optional.of(academieYear));
        when(academieYearMapper.toAcademieYearResponse(academieYear)).thenReturn(academieYearResponse);

        AcademieYearResponse response = academieYearService.getAcademieYearById(1L);

        assertNotNull(response);
        assertEquals(1L, response.getId());
    }

    @Test
    void testGetAcademieYearById_NotFound() {
        when(academieYearRepository.findById(1L)).thenReturn(Optional.empty());
        when(messageSource.getMessage(any(), any(), any(Locale.class))).thenReturn("Academic year not found");

        assertThrows(EntityNotFoundException.class, () -> academieYearService.getAcademieYearById(1L));
    }

    @Test
    void testGetAllAcademieYears() {
        when(academieYearRepository.findAll()).thenReturn(List.of(academieYear));
        when(academieYearMapper.toAcademieYearResponseList(any())).thenReturn(List.of(academieYearResponse));

        List<AcademieYearResponse> responses = academieYearService.getAllAcademieYears();

        assertFalse(responses.isEmpty());
        assertEquals(1, responses.size());
    }

    @Test
    void testUpdateAcademieYear_Success() {
        when(academieYearRepository.findById(1L)).thenReturn(Optional.of(academieYear));
        when(academieYearRepository.findByName(academieYearRequest.getName())).thenReturn(Optional.empty());
        when(academieYearRepository.save(any())).thenReturn(academieYear);
        when(academieYearMapper.toAcademieYearResponse(any())).thenReturn(academieYearResponse);

        AcademieYearResponse response = academieYearService.updateAcademieYear(academieYearRequest);

        assertNotNull(response);
        assertEquals(1L, response.getId());
    }

    @Test
    void testUpdateAcademieYear_NotFound() {
        when(academieYearRepository.findById(1L)).thenReturn(Optional.empty());
        when(messageSource.getMessage(any(), any(), any(Locale.class))).thenReturn("Academic year not found");

        assertThrows(EntityNotFoundException.class, () -> academieYearService.updateAcademieYear(academieYearRequest));
    }

    @Test
    void testDeleteAcademieYearById_Success() {
        when(academieYearRepository.findById(1L)).thenReturn(Optional.of(academieYear));
        doNothing().when(academieYearRepository).delete(academieYear);

        assertDoesNotThrow(() -> academieYearService.deleteAcademieYearById(1L));
        verify(academieYearRepository, times(1)).delete(academieYear);
    }

    @Test
    void testDeleteAcademieYearById_NotFound() {
        when(academieYearRepository.findById(1L)).thenReturn(Optional.empty());
        when(messageSource.getMessage(any(), any(), any(Locale.class))).thenReturn("Academic year not found");

        assertThrows(EntityNotFoundException.class, () -> academieYearService.deleteAcademieYearById(1L));
    }
}
