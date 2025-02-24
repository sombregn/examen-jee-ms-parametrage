package com.isi.dev.tpparametrage.classe;

import com.isi.dev.tpparametrage.classe.Classe.ClasseBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-23T16:48:12+0000",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.12 (OpenLogic)"
)
@Component
public class ClasseMapperImpl implements ClasseMapper {

    @Override
    public Classe toClasse(ClasseRequest request) {
        if ( request == null ) {
            return null;
        }

        ClasseBuilder classe = Classe.builder();

        classe.id( request.getId() );
        classe.name( request.getName() );
        classe.description( request.getDescription() );
        classe.archive( request.getArchive() );

        return classe.build();
    }

    @Override
    public ClasseResponse toClasseResponse(Classe classe) {
        if ( classe == null ) {
            return null;
        }

        ClasseResponse classeResponse = new ClasseResponse();

        classeResponse.setId( classe.getId() );
        classeResponse.setName( classe.getName() );
        classeResponse.setDescription( classe.getDescription() );
        classeResponse.setArchive( classe.getArchive() );

        return classeResponse;
    }

    @Override
    public List<ClasseResponse> toClasseResponseList(List<Classe> classes) {
        if ( classes == null ) {
            return null;
        }

        List<ClasseResponse> list = new ArrayList<ClasseResponse>( classes.size() );
        for ( Classe classe : classes ) {
            list.add( toClasseResponse( classe ) );
        }

        return list;
    }
}
