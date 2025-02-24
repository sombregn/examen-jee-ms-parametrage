package com.isi.dev.tpparametrage.academicyear;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class AcademieYearResponse {
    private Long id;
    private String name;
    private String description;
    private Boolean archive;
}
