package com.grupo2.backend.dto;

import com.grupo2.backend.entity.ConformidadEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConformidadDTO {

    private int id_conformidad;

    // Constructor that converts from entity to DTO
    public ConformidadDTO(ConformidadEntity conformidad) {
        this.id_conformidad = conformidad.getId_conformidad();
    }

    // Method to convert DTO to entity
    public  ConformidadEntity toEntity() {
        ConformidadEntity c = new ConformidadEntity();
        c.setId_conformidad(this.getId_conformidad());
        return c;
    }

    // Static method to convert from entity to DTO
    public static ConformidadDTO fromEntity(ConformidadEntity conformidad) {
        return new ConformidadDTO(conformidad);
    }
}
