package com.grupo2.backend.dto;

import java.sql.Date;

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
    private Date fecha_emitido;
    private Date fecha_entrega;
    private String estado;

    // Constructor que convierte desde la entidad a DTO
    public PedidoDto(PedidoEntity pedido) {
        this.id_pedido = pedido.getId_pedido();
        this.total = pedido.getTotal();
        this.nota_conformidad = pedido.getNota_conformidad();
        this.fecha_emitido = pedido.getFecha_emitido();
        this.fecha_entrega = pedido.getFecha_entrega();
        this.estado = pedido.getEstado();
    }

    // Método para convertir DTO a entidad
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

    // Método estático para convertir desde la entidad a DTO
    public static PedidoDto fromEntity(PedidoEntity pedido) {
        return new PedidoDto(pedido);
    }
}
