package com.isi.dev.tpparametrage.classe;

import java.util.List;

public interface ClasseService {

    ClasseResponse addClasse(ClasseRequest request);
    ClasseResponse getClasseById(Long id);
    List<ClasseResponse> getAllClasses();
    ClasseResponse updateClasse(ClasseRequest request);
    void deleteClasseById(Long id);
    ClasseResponse addSubjectToClasse(Long classeId, Long subjectId);

}
