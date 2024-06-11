package com.grupo2.backend.dto;

import com.grupo2.backend.entity.ProductoEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDto {

    private int id;

    private String nombre_producto;

    public ProductoEntity toEntity() {
        ProductoEntity e = new ProductoEntity();
        e.setId(this.getId());
        e.setNombre_producto(this.getNombre_producto());
        return e;
    }

    public ProductoDto(ProductoEntity producto) {
        this.id = producto.getId();
        this.nombre_producto = producto.getNombre_producto();
    }

    public static ProductoDto fromEntity(ProductoEntity producto) {
        return new ProductoDto(producto);
    }

}
