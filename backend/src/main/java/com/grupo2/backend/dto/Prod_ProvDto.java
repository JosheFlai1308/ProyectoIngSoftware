package com.grupo2.backend.dto;

import com.grupo2.backend.entity.Prod_Prov;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prod_ProvDto {

    private int id;
    private int precio;
    private ProductoDto producto;
    private ProveedorDto proveedor;

    public Prod_Prov toEntity() {
        Prod_Prov e = new Prod_Prov();
        return e;
    }

    public Prod_ProvDto(Prod_Prov prod_prov) {
        this.id = prod_prov.getId();
        this.precio = prod_prov.getPrecio();
        this.producto = ProductoDto.fromEntity(prod_prov.getProducto());
        this.proveedor = ProveedorDto.fromEntity(prod_prov.getProveedor());
    }

    public static Prod_ProvDto fromEntity(Prod_Prov prod_prov) {
        return new Prod_ProvDto(prod_prov);
    }
}
