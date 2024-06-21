package com.grupo2.backend.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.grupo2.backend.dto.PedidoDto;
import com.grupo2.backend.dto.Linea_detalle_pDto;
import com.grupo2.backend.dto.ProductoDto;

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
@Table(name = "Linea_detalle_p")
public class Linea_detalle_p {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_linea_detalle;

    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "subtotal")
    private int subtotal;

    @ManyToOne
    @JoinColumn(name = "FK_PRODUCTO", nullable = false, updatable = false)
    private ProductoEntity producto;

    @ManyToOne
    @JoinColumn(name = "FK_PEDIDO", nullable = false, updatable = false)
    private PedidoEntity pedido;

    public Linea_detalle_p(@JsonProperty("id_linea_detalle") int id_linea_detalle,
            @JsonProperty("cantidad") int cantidad,
            @JsonProperty("subtotal") int subtotal,
            @JsonProperty("FK_PRODUCTO") ProductoEntity producto,
            @JsonProperty("FK_PEDIDO") PedidoEntity pedido) {
        super();
        this.id_linea_detalle = id_linea_detalle;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.producto = producto;
        this.pedido = pedido;
    }

    public ProductoDto loadProductoDto(){
        ProductoDto prod = new ProductoDto();
        prod.setId(this.getProducto().getId());
        prod.setNombre_producto(this.getProducto().getNombre_producto());
        // Agregar otros campos necesarios aquí
        return prod;
    }

    public PedidoDto loadPedidoDto(){
        PedidoDto ped = new PedidoDto();
        ped.setId_pedido(this.getPedido().getId_pedido());
        ped.setTotal(this.getPedido().getTotal());
        ped.setNota_conformidad(this.getPedido().getNota_conformidad());
        ped.setFecha_emitido(this.getPedido().getFecha_emitido());
        ped.setFecha_entrega(this.getPedido().getFecha_entrega());
        ped.setEstado(this.getPedido().getEstado());
        // Agregar otros campos necesarios aquí
        return ped;
    }
    
    public Linea_detalle_pDto toDto() {
        Linea_detalle_pDto dto = new Linea_detalle_pDto();
        dto.setId_linea_detalle(this.getId_linea_detalle());
        dto.setCantidad(this.getCantidad());
        dto.setSubtotal(this.getSubtotal());
        dto.setProducto(this.loadProductoDto());
        dto.setPedido(this.loadPedidoDto());
        return dto;
    }
}
