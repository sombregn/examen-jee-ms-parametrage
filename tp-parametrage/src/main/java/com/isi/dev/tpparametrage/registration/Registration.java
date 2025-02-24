package com.isi.dev.tpparametrage.registration;

import com.isi.dev.tpparametrage.academicyear.AcademieYear;
import com.isi.dev.tpparametrage.classe.Classe;
import com.isi.dev.tpparametrage.program.Program;
import com.isi.dev.tpparametrage.student.Student;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "registration_tb")
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private String description;
    private Boolean archive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = true)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classe_id", nullable = true)
    private Classe classe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id", nullable = true)
    private Program program;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academie_year_id", nullable = false)
    private AcademieYear academieYear;

}
