package com.grupo2.backend.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.grupo2.backend.dto.EncargadoDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "encargado")
public class EncargadoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Column(name = "nombre_encargado")
    private String nombre_encargado;

    public EncargadoEntity (@JsonProperty("id") int id,
                    @JsonProperty("nombre_encargado") String nombre_encargado){
        super();
        this.id = id;
        this.nombre_encargado = nombre_encargado;
    }

    public EncargadoDto toDto(){
    EncargadoDto dto = new EncargadoDto();
    dto.setId(this.getId());
    dto.setNombre_encargado(this.getNombre_encargado());
    return dto;
    }

}
