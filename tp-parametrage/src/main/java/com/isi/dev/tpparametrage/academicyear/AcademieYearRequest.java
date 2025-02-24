package com.isi.dev.tpparametrage.academicyear;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class AcademieYearRequest {

    private Long id;
    @NotBlank(message = "L'année academie est requise")
    private String name;
    @NotBlank(message = "Veuillez donner la description")
    private String description;
    @NotNull(message = "Le champ archive ne peut pas être null")
    private Boolean archive;
}
