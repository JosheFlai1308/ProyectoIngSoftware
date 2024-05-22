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

    private String name_product;


    public ProductoEntity toEntity (){
        ProductoEntity e = new ProductoEntity();
        e.setId(this.getId());
        e.setName_product(this.getName_product());
        return e;
    }
}
