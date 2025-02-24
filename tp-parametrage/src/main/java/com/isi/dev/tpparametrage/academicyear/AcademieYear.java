package com.isi.dev.tpparametrage.academicyear;

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
@Table(name = "academieYear_tb")

public class AcademieYear {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Boolean archive;
    @OneToMany(mappedBy = "academieYear", cascade = CascadeType.ALL)
    private List<Registration> registrations;
    public AcademieYear(Long id, String name, String description, Boolean archive) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.archive = archive;
    }
}
