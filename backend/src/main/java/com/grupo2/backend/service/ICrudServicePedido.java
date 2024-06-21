package com.grupo2.backend.service;

import java.util.List;
import java.util.Optional;

import com.grupo2.backend.dto.PedidoDto;

public interface ICrudServicePedido {

    List<PedidoDto> findAll(String search);

    Optional<PedidoDto> findById(int id);

    PedidoDto save(PedidoDto dto);

    void delete(PedidoDto dto);
}
