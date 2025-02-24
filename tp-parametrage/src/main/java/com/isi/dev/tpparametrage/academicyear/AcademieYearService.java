package com.isi.dev.tpparametrage.academicyear;

import java.util.List;

public interface AcademieYearService {
    AcademieYearResponse addAcademieYear(AcademieYearRequest request);
    AcademieYearResponse getAcademieYearById(Long id);
    List<AcademieYearResponse> getAllAcademieYears();
    AcademieYearResponse updateAcademieYear(AcademieYearRequest request);
    void deleteAcademieYearById(Long id);
}
