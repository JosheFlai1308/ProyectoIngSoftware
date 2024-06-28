package com.grupo2.backend.dto;

import java.util.List;

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
    private List<Integer> notas;
    private float calificacion;

    public ProveedorEntity toEntity() {
        ProveedorEntity e = new ProveedorEntity();
        e.setId(this.getId());
        e.setNombre_proveedor(this.getNombre_proveedor());
        e.setCategoria(this.getCategoria());
        e.setNotas(this.getNotas());
        e.setCalificacion(this.getCalificacion());
        return e;
    }
}
