package com.isi.dev.tpparametrage.classe;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClasseRequest {

    private Long id;
    @NotBlank(message = "Le name est requis")
    private String name;
    @NotBlank(message = "Veuillez donner la description")
    private String description;
    @NotNull(message = "Le champ archive ne peut pas être null")
    private Boolean archive;
}