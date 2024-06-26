package com.grupo2.frontend.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProveedorDto {
    private int id;
    private String nombre_proveedor;
    private String categoria;
    private List<Float> notas;
    private float calificacion;
}
