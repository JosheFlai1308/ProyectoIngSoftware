package com.grupo2.frontend.service;

import java.util.List;
import java.util.Optional;

import com.grupo2.frontend.dto.Guia_DespachoDto;

public interface ICrudServiceGuia_Despacho {
    public List<Guia_DespachoDto> findAllREST(String search) throws Exception;

	public Optional<Guia_DespachoDto> findByIdREST(int id) throws Exception;

	public Guia_DespachoDto editarREST(Guia_DespachoDto dto) throws Exception;

	public Guia_DespachoDto saveREST(Guia_DespachoDto dto) throws Exception;

	public Guia_DespachoDto deleteREST(int id) throws Exception;
}
