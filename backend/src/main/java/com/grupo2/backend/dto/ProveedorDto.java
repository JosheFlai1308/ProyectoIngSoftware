package com.grupo2.backend.dto;

import com.grupo2.backend.entity.ProveedorEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProveedorDto {

    private int id;
    private String nombre_proveedor;
    private String categoria;

    public ProveedorEntity toEntity() {
        ProveedorEntity e = new ProveedorEntity();
        e.setId(this.getId());
        e.setNombre_proveedor(this.getNombre_proveedor());
        e.setCategoria(this.getCategoria());
        return e;
    }

    public ProveedorDto(ProveedorEntity proveedor) {
        this.id = proveedor.getId();
        this.nombre_proveedor = proveedor.getNombre_proveedor();
        this.categoria = proveedor.getCategoria();
    }

    public static ProveedorDto fromEntity(ProveedorEntity proveedor) {
        return new ProveedorDto(proveedor);
    }
}
