package com.grupo2.backend.entity;

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
@Table(name="prod_prov")
public class Prod_Prov {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="precio")
    private int precio;

    @ManyToOne
    @JoinColumn(name= "FK_PRODUCTO", nullable = false, updatable = false)
    private ProductoEntity productoEntity;

    @ManyToOne
    @JoinColumn(name = "FK_PROVEEDOR", nullable =  false, updatable = false)
    private ProveedorEntity proveedorEntity;


    
}
