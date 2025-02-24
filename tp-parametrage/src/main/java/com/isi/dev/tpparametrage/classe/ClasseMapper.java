package com.isi.dev.tpparametrage.classe;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface ClasseMapper {
    Classe toClasse(ClasseRequest request);
    ClasseResponse toClasseResponse(Classe classe);
    List<ClasseResponse> toClasseResponseList(List<Classe> classes);
}
