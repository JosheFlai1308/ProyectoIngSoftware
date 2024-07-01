package com.grupo2.backend.dto;

import java.sql.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.grupo2.backend.entity.PedidoEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDto {

    private int id_pedido;
    private int total;
    private String nota_conformidad;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_emitido;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_entrega;

    private String estado;


    // Constructor that converts from entity to DTO
    public PedidoDto(PedidoEntity pedido) {
        this.id_pedido = pedido.getId_pedido();
        this.total = pedido.getTotal();
        this.nota_conformidad = pedido.getNota_conformidad();
        this.fecha_emitido = pedido.getFecha_emitido();
        this.fecha_entrega = pedido.getFecha_entrega();
        this.estado = pedido.getEstado();
    }

    // Method to convert DTO to entity
    public PedidoEntity toEntity() {
        PedidoEntity e = new PedidoEntity();
        e.setId_pedido(this.getId_pedido());
        e.setTotal(this.getTotal());
        e.setNota_conformidad(this.getNota_conformidad());
        e.setFecha_emitido(this.getFecha_emitido());
        e.setFecha_entrega(this.getFecha_entrega());
        e.setEstado(this.getEstado());
        return e;
    }

    // Static method to convert from entity to DTO
    public static PedidoDto fromEntity(PedidoEntity pedido) {
        return new PedidoDto(pedido);
    }
}
