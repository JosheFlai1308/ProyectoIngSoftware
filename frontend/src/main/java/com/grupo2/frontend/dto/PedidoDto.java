package com.grupo2.frontend.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

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

    private Guia_DespachoDto guiaDespachoDto;
}
