package com.grupo2.frontend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineadetalleCDTO {

    private int id;
    private String criterio;
    private int pje_criterio;
    private ConformidadDTO conformidad;
}
