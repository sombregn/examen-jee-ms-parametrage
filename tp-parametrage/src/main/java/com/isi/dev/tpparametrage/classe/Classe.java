package com.isi.dev.tpparametrage.classe;

import com.isi.dev.tpparametrage.registration.Registration;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "classe_tb")
public class Classe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Boolean archive;
    @OneToMany(mappedBy = "classe", cascade = CascadeType.ALL)
    private List<Registration> registrations;

    public Classe(long id, String name, String description, Boolean archive) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.archive = archive;
    }
}
