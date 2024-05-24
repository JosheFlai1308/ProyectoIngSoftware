package com.grupo2.backend.entity;

import jakarta.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.grupo2.backend.dto.EncargadoDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
    @Column(name = "name_encargado")
    private String nameEncargado;

    public EncargadoEntity (@JsonProperty("id") int id,
                    @JsonProperty("name_encargado") String nameEncargado){
        super();
        this.id = id;
        this.nameEncargado = nameEncargado;
    }

    public EncargadoDto toDto(){
    EncargadoDto dto = new EncargadoDto();
    dto.setId(this.getId());
    dto.setNameEncargado(this.getNameEncargado());
    return dto;
    }

}
