package com.isi.dev.tpparametrage.academicyear;

import com.isi.dev.tpparametrage.academicyear.AcademieYear.AcademieYearBuilder;
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
public class AcademieYearMapperImpl implements AcademieYearMapper {

    @Override
    public AcademieYear toAcademieYear(AcademieYearRequest request) {
        if ( request == null ) {
            return null;
        }

        AcademieYearBuilder academieYear = AcademieYear.builder();

        academieYear.id( request.getId() );
        academieYear.name( request.getName() );
        academieYear.description( request.getDescription() );
        academieYear.archive( request.getArchive() );

        return academieYear.build();
    }

    @Override
    public AcademieYearResponse toAcademieYearResponse(AcademieYear AcademieYear) {
        if ( AcademieYear == null ) {
            return null;
        }

        AcademieYearResponse academieYearResponse = new AcademieYearResponse();

        academieYearResponse.setId( AcademieYear.getId() );
        academieYearResponse.setName( AcademieYear.getName() );
        academieYearResponse.setDescription( AcademieYear.getDescription() );
        academieYearResponse.setArchive( AcademieYear.getArchive() );

        return academieYearResponse;
    }

    @Override
    public List<AcademieYearResponse> toAcademieYearResponseList(List<AcademieYear> AcademieYears) {
        if ( AcademieYears == null ) {
            return null;
        }

        List<AcademieYearResponse> list = new ArrayList<AcademieYearResponse>( AcademieYears.size() );
        for ( AcademieYear academieYear : AcademieYears ) {
            list.add( toAcademieYearResponse( academieYear ) );
        }

        return list;
    }
}
