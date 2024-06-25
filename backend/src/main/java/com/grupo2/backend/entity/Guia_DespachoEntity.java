package com.grupo2.backend.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.grupo2.backend.dto.Guia_DespachoDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Entity
@Table(name = "guia_despacho")
public class Guia_DespachoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(name = "cantidad_recibida")
    private int cantidad_recibida;

    @Column(name = "fecha")
    private LocalDate fecha;

    @OneToMany(mappedBy = "guia_despacho", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EncargadoEntity> encargados;

    public Guia_DespachoEntity(@JsonProperty("id") int id, @JsonProperty("cantidad_recibida") int cantidad_recibida, @JsonProperty("fecha") LocalDate fecha) {
        super();
        this.id = id;
        this.cantidad_recibida = cantidad_recibida;
        this.fecha = fecha;
    }

    public Guia_DespachoDto toDto(){
        Guia_DespachoDto dto = new Guia_DespachoDto();
    dto.setId(this.getId());
    dto.setCantidad_recibida(this.getCantidad_recibida());
    dto.setFecha(this.getFecha());
    return dto;
    }

}
