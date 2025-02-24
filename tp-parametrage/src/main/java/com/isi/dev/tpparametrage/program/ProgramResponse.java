package com.isi.dev.tpparametrage.program;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProgramResponse {
    private Long id;
    private String name;
    private String description;
    private Boolean archive;
}
