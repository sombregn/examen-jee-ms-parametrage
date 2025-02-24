package com.isi.dev.tpparametrage.student;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequest {
    private Long id;

    @NotBlank(message = "Le prenom est requis!")
    private String firstName;

    @NotBlank(message = "Le nom est requis!")
    private String lastName;

    @NotBlank(message = "email est requis!")
    @Email(message = "email doit etre valide")
    private String emailPro;

    @NotBlank(message = "email est requis!")
    @Email(message = "email doit etre valide")
    private String emailPerso;

    @NotBlank(message = "le numero de tel est requis")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Le numéro de téléphone doit être valide (ex :+1234567890)")
    private String phoneNumber;

    @NotBlank(message = "addresse est requise!")
    private String address;

    @NotNull(message = "Le champ archive ne peut pas être null")
    private Boolean archive;

    @NotBlank(message = "Le numero de registration est requis!")
    private String registrationNu;
}
