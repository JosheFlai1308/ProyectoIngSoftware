package com.grupo2.frontend.service;

import java.util.List;
import java.util.Optional;

import com.grupo2.frontend.dto.Guia_DespachoDto;

public interface ICrudServiceGuia_Despacho {
    List<Guia_DespachoDto> findAllREST(String search) throws Exception;

    Optional<Guia_DespachoDto> findByIdREST(int id) throws Exception;

    Guia_DespachoDto editarREST(Guia_DespachoDto dto) throws Exception;

    Guia_DespachoDto saveREST(Guia_DespachoDto dto) throws Exception;

    Guia_DespachoDto deleteREST(int id) throws Exception;
}
