package com.grupo2.backend.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.grupo2.backend.dto.ProductoDto;

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
@Table (name="producto")
public class ProductoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Column(name="nombre_producto")
    private String nombre_producto;


    public ProductoEntity (@JsonProperty("id") int id,
                            @JsonProperty("nombre_producto") String nombre_producto){
        super();
        this.id = id;
        this.nombre_producto = nombre_producto;
    }

    public ProductoDto toDto(){
        ProductoDto dto = new ProductoDto();
        dto.setId(this.getId());
        dto.setNombre_producto(this.getNombre_producto());
        return dto;
    }
}