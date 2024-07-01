package com.grupo2.frontend.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Guia_DespachoDto {
    
    private int id;
    private LocalDate fecha;
    private int cantidad_recibida;
    private int cantidad_esperada;
    private List<EncargadoDto> encargados;
    private Integer encargadoId; // Para la relación con Encargado
    private String encargadoNombre; // Para mostrar el nombre del Encargado
}
