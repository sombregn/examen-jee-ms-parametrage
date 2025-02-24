package com.isi.dev.tpparametrage.program;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ProgramMapper {
    Program toProgram(ProgramRequest request);
    ProgramResponse toProgramResponse(Program program);
    List<ProgramResponse> toProgramResponseList(List<Program> programs);
}