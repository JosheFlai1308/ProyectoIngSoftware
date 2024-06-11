package com.grupo2.backend.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.grupo2.backend.dto.ProveedorDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "proveedor")
public class ProveedorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Column(name = "nombre_producto")
    private String nombre_proveedor;

    @Column(name = "categoria")
    private String categoria;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proveedor")
    private List<Prod_Prov> prod_prov;

    public ProveedorEntity(@JsonProperty("id") int id,
            @JsonProperty("nombre_proveedor") String nombre_proveedor,
            @JsonProperty("categoria") String categoria) {
        super();
        this.id = id;
        this.nombre_proveedor = nombre_proveedor;
        this.categoria = categoria;
    }

    public ProveedorDto toDto() {
        ProveedorDto dto = new ProveedorDto();
        dto.setId(this.getId());
        dto.setNombre_proveedor(this.getNombre_proveedor());
        dto.setCategoria(this.getCategoria());
        return dto;
    }
}
