package com.isi.dev.tpparametrage.program;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.isi.dev.tpparametrage.exception.EntityExistsException;
import com.isi.dev.tpparametrage.exception.EntityNotFoundException;
import com.isi.dev.tpparametrage.program.*;
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
class ProgramServiceImplTest {

    @Mock
    private ProgramRepository programRepository;

    @Mock
    private ProgramMapper programMapper;

    @Mock
    private MessageSource messageSource;

    @InjectMocks
    private ProgramServiceImpl programService;

    private ProgramRequest programRequest;
    private Program program;
    private ProgramResponse programResponse;

    @BeforeEach
    void setUp() {
        programRequest = new ProgramRequest();
        programRequest.setId(1L);
        programRequest.setName("Test Program");

        program = new Program();
        program.setId(1L);
        program.setName("Test Program");

        programResponse = new ProgramResponse();
        programResponse.setId(1L);
        programResponse.setName("Test Program");
    }

    @Test
    void testAddProgram_Success() {
        when(programRepository.findByName(programRequest.getName())).thenReturn(Optional.empty());
        when(programMapper.toProgram(programRequest)).thenReturn(program);
        when(programRepository.save(program)).thenReturn(program);
        when(programMapper.toProgramResponse(program)).thenReturn(programResponse);

        ProgramResponse response = programService.addProgram(programRequest);

        assertNotNull(response);
        assertEquals("Test Program", response.getName());
        verify(programRepository).save(program);
    }

    @Test
    void testAddProgram_ThrowsEntityExistsException() {
        when(programRepository.findByName(programRequest.getName())).thenReturn(Optional.of(program));
        when(messageSource.getMessage(anyString(), any(), any(Locale.class))).thenReturn("Program already exists");

        assertThrows(EntityExistsException.class, () -> programService.addProgram(programRequest));
    }

    @Test
    void testGetProgram_Success() {
        when(programRepository.findById(1L)).thenReturn(Optional.of(program));
        when(programMapper.toProgramResponse(program)).thenReturn(programResponse);

        ProgramResponse response = programService.getProgram(1L);

        assertNotNull(response);
        assertEquals(1L, response.getId());
    }

    @Test
    void testGetProgram_ThrowsEntityNotFoundException() {
        when(programRepository.findById(1L)).thenReturn(Optional.empty());
        when(messageSource.getMessage(anyString(), any(), any(Locale.class))).thenReturn("Program not found");

        assertThrows(EntityNotFoundException.class, () -> programService.getProgram(1L));
    }

    @Test
    void testGetAllPrograms() {
        when(programRepository.findAll()).thenReturn(List.of(program));
        when(programMapper.toProgramResponseList(List.of(program))).thenReturn(List.of(programResponse));

        List<ProgramResponse> responses = programService.getAllPrograms();

        assertFalse(responses.isEmpty());
        assertEquals(1, responses.size());
    }

    @Test
    void testUpdateProgram_Success() {
        when(programRepository.findById(programRequest.getId())).thenReturn(Optional.of(program));
        when(programRepository.save(any(Program.class))).thenReturn(program);
        when(programMapper.toProgramResponse(program)).thenReturn(programResponse);

        ProgramResponse response = programService.updateProgram(programRequest);

        assertNotNull(response);
        assertEquals("Test Program", response.getName());
    }

    @Test
    void testUpdateProgram_ThrowsEntityNotFoundException() {
        when(programRepository.findById(programRequest.getId())).thenReturn(Optional.empty());
        when(messageSource.getMessage(anyString(), any(), any(Locale.class))).thenReturn("Program not found");

        assertThrows(EntityNotFoundException.class, () -> programService.updateProgram(programRequest));
    }

    @Test
    void testDeleteProgramById_Success() {
        when(programRepository.findById(1L)).thenReturn(Optional.of(program));

        programService.deleteProgramById(1L);

        verify(programRepository).delete(program);
    }

    @Test
    void testDeleteProgramById_ThrowsEntityNotFoundException() {
        when(programRepository.findById(1L)).thenReturn(Optional.empty());
        when(messageSource.getMessage(anyString(), any(), any(Locale.class))).thenReturn("Program not found");

        assertThrows(EntityNotFoundException.class, () -> programService.deleteProgramById(1L));
    }
}
