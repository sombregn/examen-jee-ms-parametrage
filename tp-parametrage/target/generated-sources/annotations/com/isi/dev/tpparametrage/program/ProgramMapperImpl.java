package com.isi.dev.tpparametrage.program;

import com.isi.dev.tpparametrage.program.Program.ProgramBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-23T16:48:13+0000",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.12 (OpenLogic)"
)
@Component
public class ProgramMapperImpl implements ProgramMapper {

    @Override
    public Program toProgram(ProgramRequest request) {
        if ( request == null ) {
            return null;
        }

        ProgramBuilder program = Program.builder();

        program.id( request.getId() );
        program.name( request.getName() );
        program.description( request.getDescription() );
        program.archive( request.getArchive() );

        return program.build();
    }

    @Override
    public ProgramResponse toProgramResponse(Program program) {
        if ( program == null ) {
            return null;
        }

        ProgramResponse programResponse = new ProgramResponse();

        programResponse.setId( program.getId() );
        programResponse.setName( program.getName() );
        programResponse.setDescription( program.getDescription() );
        programResponse.setArchive( program.getArchive() );

        return programResponse;
    }

    @Override
    public List<ProgramResponse> toProgramResponseList(List<Program> programs) {
        if ( programs == null ) {
            return null;
        }

        List<ProgramResponse> list = new ArrayList<ProgramResponse>( programs.size() );
        for ( Program program : programs ) {
            list.add( toProgramResponse( program ) );
        }

        return list;
    }
}
