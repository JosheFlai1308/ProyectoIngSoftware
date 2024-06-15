package com.grupo2.backend.dto;

import java.time.LocalDate;

import com.grupo2.backend.entity.Guia_DespachoEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Guia_DespachoDto {
    private int id;
    private int cantidad_recibida;
    private LocalDate fecha;

    public Guia_DespachoEntity toEntity() {
        Guia_DespachoEntity g = new Guia_DespachoEntity();
        g.setId(this.getId());
        g.setCantidad_recibida(this.getCantidad_recibida());
        g.setFecha(this.getFecha());
        return g;
    }
    
    public static Guia_DespachoDto fromEntity(Guia_DespachoEntity guia) {
        return new Guia_DespachoDto(guia.getId(), guia.getCantidad_recibida(), guia.getFecha());
    }
}


