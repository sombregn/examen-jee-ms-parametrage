package com.isi.dev.tpparametrage.program;

import java.util.List;

public interface ProgramService {
    ProgramResponse addProgram(ProgramRequest request);
    ProgramResponse getProgram(Long id);
    List<ProgramResponse> getAllPrograms();
    ProgramResponse updateProgram(ProgramRequest request);
    void deleteProgramById(Long id);
}

