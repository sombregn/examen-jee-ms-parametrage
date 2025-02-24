package com.isi.dev.tpparametrage.academicyear;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface AcademieYearMapper {
    AcademieYear toAcademieYear(AcademieYearRequest request);
    AcademieYearResponse toAcademieYearResponse(AcademieYear AcademieYear);
    List<AcademieYearResponse> toAcademieYearResponseList(List<AcademieYear> AcademieYears);
}
