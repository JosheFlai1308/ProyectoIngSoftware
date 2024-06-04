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


    public ProductoEntity toEntity (){
        ProductoEntity e = new ProductoEntity();
        e.setId(this.getId());
        e.setNombre_producto(this.getNombre_producto());
        return e;
    }
}
