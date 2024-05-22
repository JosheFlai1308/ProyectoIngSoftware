package com.grupo2.backend.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@NoargsConstructor
@Entity
@Table (name="Producto")
public class ProductoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Column(name="name_product")
    private String name_product;


    public ProductoEntity (@JsonProperty("id") int id,
                            @JsonProperty("name_product") String name_product){
        super();
        this.id = id;
        this.name_product = name_product;
    }
}