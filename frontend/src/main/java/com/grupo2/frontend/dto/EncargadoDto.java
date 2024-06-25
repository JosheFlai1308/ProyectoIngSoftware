package com.grupo2.frontend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EncargadoDto {
    private int id;
    private String nombre_encargado;
    private String numero_telefono;
    private String correo_electronico;
    private String password;
}
