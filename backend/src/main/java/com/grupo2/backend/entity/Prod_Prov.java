package com.grupo2.backend.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.grupo2.backend.dto.Prod_ProvDto;
import com.grupo2.backend.dto.ProductoDto;
import com.grupo2.backend.dto.ProveedorDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "prod_prov")
public class Prod_Prov {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "precio")
    private int precio;

    @ManyToOne
    @JoinColumn(name = "FK_PRODUCTO", nullable = false, updatable = false)
    private ProductoEntity producto;

    @ManyToOne
    @JoinColumn(name = "FK_PROVEEDOR", nullable = false, updatable = false)
    private ProveedorEntity proveedor;

    public Prod_Prov(@JsonProperty("id") int id,
            @JsonProperty("precio") int precio,
            @JsonProperty("FK_PRODUCTO") ProductoEntity producto,
            @JsonProperty("FK_PROVEEDOR") ProveedorEntity proveedor) {
        super();
        this.id = id;
        this.precio = precio;
        this.producto = producto;
        this.proveedor = proveedor;
    }

    public ProductoDto loadProductoDto(){
        ProductoDto prod = new ProductoDto();
        prod.setId(this.getProducto().getId());
        prod.setNombre_producto(this.getProducto().getNombre_producto());
        return prod;
    }

    public ProveedorDto loadProveedorDto(){
        ProveedorDto prov = new ProveedorDto();
        prov.setId(this.getProveedor().getId());
        prov.setNombre_proveedor(this.getProveedor().getNombre_proveedor());
        prov.setCategoria(this.getProveedor().getCategoria());
        return prov;
    }
    
    public Prod_ProvDto toDto() {
        Prod_ProvDto dto = new Prod_ProvDto();
        dto.setId(this.getId());
        dto.setPrecio(this.getPrecio());
        dto.setProducto(this.loadProductoDto());
        dto.setProveedor(this.loadProveedorDto());
        return dto;
    }


}
