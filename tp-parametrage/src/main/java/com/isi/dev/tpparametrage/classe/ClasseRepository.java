package com.isi.dev.tpparametrage.classe;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClasseRepository extends JpaRepository<Classe,Long> {
    Optional<Classe> findByName(String name);
}
