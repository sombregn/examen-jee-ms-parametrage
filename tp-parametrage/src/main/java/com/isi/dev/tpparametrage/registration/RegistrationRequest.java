package com.isi.dev.tpparametrage.registration;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class RegistrationRequest {

    private Long id;
    @NotNull(message = "La date registration est requise")
    private LocalDate date;

    @NotBlank(message = "Veuillez donner la description")

    private String description;

    @NotNull(message = "Le champ archive ne peut pas être null")
    private Boolean archive;

    @NotNull(message = "Donner l'Id d'Etudiant")
    private Long studentId;

//    @NotNull(message = "Donner l'Id d'utilisateur")
//    private Long administrativeAgentId;

    @NotNull(message = "Donner l'Id de la Classe")
    private Long classeId;

    @NotNull(message = "Donner l'Id de programme")
    private Long programId;

    @NotNull(message = "Donner l'Id Academie year")
    private Long academieYearId;


    // Constructeur personnalisé pour les tests ou autres besoins
    public RegistrationRequest(Long id, LocalDate date, String description, Boolean archive, Long studentId, Long classeId, Long programId, Long academieYearId) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.archive = archive;
        this.studentId = studentId;
        this.classeId = classeId;
        this.programId = programId;
        this.academieYearId = academieYearId;
    }

}