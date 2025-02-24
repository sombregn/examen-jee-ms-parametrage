package com.isi.dev.tpparametrage.academicyear;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AcademieYearRepository extends JpaRepository<AcademieYear, Long> {
    Optional<AcademieYear> findByName(String name);
}
