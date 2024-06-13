package com.grupo2.frontend.dto;

import java.time.LocalDate;

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
}
