package com.grupo2.backend.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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
    private int cantidad_esperada;
    private LocalDate fecha;
    private List<EncargadoDto> encargados;

    public Guia_DespachoEntity toEntity(){
        Guia_DespachoEntity g = new Guia_DespachoEntity();
        g.setId(this.getId());
        g.setCantidad_recibida(this.getCantidad_recibida());
        g.setFecha(this.getFecha());
        g.setCantidad_esperada((this.getCantidad_esperada()));
        if (this.getEncargados() != null) {
            g.setEncargados(this.getEncargados().stream().map(EncargadoDto::toEntity).collect(Collectors.toList()));
        }
        return g;
    }

    public Guia_DespachoDto(Guia_DespachoEntity guia ){
        this.id = guia.getId();
        this.cantidad_recibida = guia.getCantidad_recibida();
        this.cantidad_esperada = guia.getCantidad_esperada();
        this.fecha = guia.getFecha();
        if (guia.getEncargados() != null) {
            this.encargados = guia.getEncargados().stream().map(EncargadoDto::new).collect(Collectors.toList());
        }
    }

    public static Guia_DespachoDto fromEntity(Guia_DespachoEntity guia){
        return new Guia_DespachoDto(guia);
    }
}
