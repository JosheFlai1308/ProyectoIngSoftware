package com.grupo2.frontend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EncargadoDto {
    private int id;
    private String nombreEncargado;
    private String numeroTelefono;
    private String correoElectronico;
}
