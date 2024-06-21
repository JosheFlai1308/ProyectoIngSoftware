package com.grupo2.frontend.service;

import java.util.List;
import java.util.Optional;

import com.grupo2.frontend.dto.PedidoDto;

public interface ICrudServicePedido {

    public List<PedidoDto> findAllREST(String search) throws Exception;

    public Optional<PedidoDto> findByIdREST(int id) throws Exception;

    public PedidoDto editarREST(PedidoDto dto) throws Exception;

    public PedidoDto saveREST(PedidoDto dto) throws Exception;

    public PedidoDto deleteREST(int id) throws Exception;
}
