package com.isi.dev.tpparametrage.program;

import com.isi.dev.tpparametrage.exception.EntityExistsException;
import com.isi.dev.tpparametrage.exception.EntityNotFoundException;
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
public class ProgramServiceImpl implements ProgramService {
    private final ProgramRepository programRepository;
    private final ProgramMapper programMapper;
    private final MessageSource messageSource;

    @Override
    public ProgramResponse addProgram(ProgramRequest request) {
        if (programRepository.findByName(request.getName()).isPresent()) {
            throw new EntityExistsException(messageSource.getMessage("program.name.exist", new Object[]{request.getName()}, Locale.getDefault()));
        }
        Program program = programMapper.toProgram(request);
        var savedProgram = programRepository.save(program);
        return programMapper.toProgramResponse(savedProgram);
    }

    @Override
    public ProgramResponse getProgram(Long id) {
        return programRepository.findById(id)
                .map(programMapper::toProgramResponse)
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("program.notfound", new Object[]{id}, Locale.getDefault())));
    }

    @Override
    public List<ProgramResponse> getAllPrograms() {
        List<Program> programs = programRepository.findAll();
        return programMapper.toProgramResponseList(programs);
    }

    @Override
    public ProgramResponse updateProgram(ProgramRequest request) {
        var program = programRepository.findById(request.getId())
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("program.notfound", new Object[]{request.getId()}, Locale.getDefault())));
        program.setName(request.getName());
        program.setDescription(request.getDescription());
        program.setArchive(request.getArchive());
        var updatedProgram = programRepository.save(program);
        return programMapper.toProgramResponse(updatedProgram);
    }

    @Override
    public void deleteProgramById(Long id) {
        Program program = programRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("program.notfound", new Object[]{id}, Locale.getDefault())));
        programRepository.delete(program);

    }

}

