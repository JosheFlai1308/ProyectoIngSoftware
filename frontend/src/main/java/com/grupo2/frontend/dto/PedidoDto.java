package com.grupo2.frontend.dto;

import java.util.Date;

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
}
