package com.grupo2.frontend.service;

import java.util.List;
import java.util.Optional;

import com.grupo2.frontend.dto.LineadetalleCDTO;

public interface ICrudServiceLineadetalleC {
	List<LineadetalleCDTO> findAllREST(String search) throws Exception;

	Optional<LineadetalleCDTO> findByIdREST(int id) throws Exception;

	LineadetalleCDTO editarREST(LineadetalleCDTO p) throws Exception;

	LineadetalleCDTO saveREST(LineadetalleCDTO p) throws Exception;

	LineadetalleCDTO deleteREST(int id) throws Exception;
}
