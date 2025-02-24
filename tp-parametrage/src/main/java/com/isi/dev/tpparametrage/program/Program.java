package com.isi.dev.tpparametrage.program;

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
@Table(name = "program_tb")
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Boolean archive;
    @OneToMany(mappedBy = "program", cascade = CascadeType.ALL)
    private List<Registration> registrations;

    public Program(Long id , String name, String description, Boolean archive) {
        this.name = name;
        this.description = description;
        this.archive = archive;
    }

}
