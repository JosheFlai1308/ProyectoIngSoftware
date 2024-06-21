package com.grupo2.backend.dto;

import com.grupo2.backend.entity.Linea_detalle_p;
import com.grupo2.backend.entity.PedidoEntity;
import com.grupo2.backend.entity.ProductoEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Linea_detalle_pDto {

    private int id_linea_detalle;
    private int cantidad;
    private int subtotal;
    private ProductoDto producto;
    private PedidoDto pedido;

    public ProductoEntity loadProductoEntity(){
        ProductoEntity prod = new ProductoEntity();
        prod.setId(this.getProducto().getId());
        prod.setNombre_producto(this.getProducto().getNombre_producto());
        // Agregar otros campos necesarios aquí
        return prod;
    }

    public PedidoEntity loadPedidoEntity(){
        PedidoEntity ped = new PedidoEntity();
        ped.setId_pedido(this.getPedido().getId_pedido());
        ped.setTotal(this.getPedido().getTotal());
        ped.setNota_conformidad(this.getPedido().getNota_conformidad());
        ped.setFecha_emitido(this.getPedido().getFecha_emitido());
        ped.setFecha_entrega(this.getPedido().getFecha_entrega());
        ped.setEstado(this.getPedido().getEstado());
        // Agregar otros campos necesarios aquí
        return ped;
    }

    public Linea_detalle_p toEntity() {
        Linea_detalle_p e = new Linea_detalle_p();
        e.setId_linea_detalle(this.getId_linea_detalle());
        e.setCantidad(this.getCantidad());
        e.setSubtotal(this.getSubtotal());
        e.setProducto(this.loadProductoEntity());
        e.setPedido(this.loadPedidoEntity());
        return e;
    }

    public static Linea_detalle_pDto fromEntity(Linea_detalle_p entity) {
        Linea_detalle_pDto dto = new Linea_detalle_pDto();
        dto.setId_linea_detalle(entity.getId_linea_detalle());
        dto.setCantidad(entity.getCantidad());
        dto.setSubtotal(entity.getSubtotal());
        dto.setProducto(entity.loadProductoDto());
        dto.setPedido(entity.loadPedidoDto());
        return dto;
    }
}
