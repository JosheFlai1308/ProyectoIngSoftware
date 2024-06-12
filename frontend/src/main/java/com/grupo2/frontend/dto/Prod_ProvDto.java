package com.grupo2.frontend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prod_ProvDto {

    private int id;
    private int precio;
    private String id_producto;
    private ProductoDto producto;
    private String id_proveedor;
    private ProveedorDto proveedor;
}
