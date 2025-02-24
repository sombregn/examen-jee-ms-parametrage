package com.isi.dev.tpparametrage.classe;

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
public class ClasseServiceImpl implements ClasseService {

    private final ClasseRepository classeRepository;
    private final ClasseMapper classeMapper;
    private final MessageSource messageSource;

    @Override
    public ClasseResponse addClasse(ClasseRequest request) {

        if (classeRepository.findByName(request.getName()).isPresent()) {
            throw new EntityExistsException(messageSource.getMessage("classe.exists", new Object[]{request.getName()}, Locale.getDefault()));
        }

        Classe classe = classeMapper.toClasse(request);
        var savedClass = classeRepository.save(classe);
        return classeMapper.toClasseResponse(savedClass);
    }

    @Override
    public ClasseResponse getClasseById(Long id) {
        return classeRepository.findById(id)
                .map(classeMapper::toClasseResponse)
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("classe.notfound", new Object[]{id}, Locale.getDefault())));
    }

    @Override
    public List<ClasseResponse> getAllClasses() {
        return classeMapper.toClasseResponseList(classeRepository.findAll());
    }

    @Override
    public ClasseResponse updateClasse(ClasseRequest request) {

        classeRepository.findByName(request.getName())
                .ifPresent(existingNum -> {
                    if (!existingNum.getId().equals(request.getId())) {
                        throw new EntityExistsException(messageSource.getMessage("classe.name.exists", new Object[]{existingNum}, Locale.getDefault() ));
                    }
                });

        var classe = classeRepository.findById(request.getId())
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("classe.notfound", new Object[]{request.getId()}, Locale.getDefault())));
        classe.setName(request.getName());
        classe.setDescription(request.getDescription());
        classe.setArchive(request.getArchive());
        var updatedClass = classeRepository.save(classe);
        return classeMapper.toClasseResponse(updatedClass);
    }

    @Override
    public void deleteClasseById(Long id) {
        Classe classe = classeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("classe.notfound", new Object[]{id}, Locale.getDefault())));
        classeRepository.delete(classe);
    }

    @Override
    public ClasseResponse addSubjectToClasse(Long classeId, Long subjectId) {
//        var classe = classeRepository.findById(classeId)
//                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("classe.notfound", new Object[]{classeId}, Locale.getDefault() )));
//        var subject = subjectRepository.findById(subjectId)
//                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("subject.notfound", new Object[]{subjectId}, Locale.getDefault() )));
//        classe.getSubjects().add(subject);
//        subject.getClasses().add(classe);
//        return classeMapper.toClasseResponse(classeRepository.save(classe));
        return null;
    }

}
