package com.grupo2.frontend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Linea_detalle_pDto {

    private int id;
    private int cantidad;
    private int id_producto;
    private ProductoDto producto;
    private int id_pedido;
    private PedidoDto pedido;
}
