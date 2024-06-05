package com.grupo2.frontend.service;

import java.util.List;
import java.util.Optional;

import com.grupo2.frontend.dto.EncargadoDto;

public interface ICrudServiceEncargado {
    	public List<EncargadoDto> findAllREST(String search) throws Exception;

	public Optional<EncargadoDto> findByIdREST(int id) throws Exception;

	public EncargadoDto editarREST(EncargadoDto dto) throws Exception;

	public EncargadoDto saveREST(EncargadoDto dto) throws Exception;

	public EncargadoDto deleteREST(int id) throws Exception;
}
