package com.grupo2.backend.dto;

import com.grupo2.backend.entity.Prod_Prov;
import com.grupo2.backend.entity.ProductoEntity;
import com.grupo2.backend.entity.ProveedorEntity;

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
        e.setId(this.getId());
        e.setPrecio(this.getPrecio());
        ProductoEntity prod = new ProductoEntity();
        prod.setId(this.getProducto().getId());
        prod.setNombre_producto(this.getProducto().getNombre_producto());
        e.setProducto(prod);
        ProveedorEntity prov = new ProveedorEntity();
        prov.setId(this.getProveedor().getId());
        prov.setNombre_proveedor(this.getProveedor().getNombre_proveedor());
        prov.setCategoria(this.getProveedor().getCategoria());
        e.setProveedor(prov);
        return e;
    }
}
