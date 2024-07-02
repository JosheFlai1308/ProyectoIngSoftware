package com.grupo2.frontend.service;

import java.util.List;
import java.util.Optional;

import com.grupo2.frontend.dto.Linea_detalle_pDto;

public interface ICrudServiceLinea_detalle_p {

	List<Linea_detalle_pDto> findAllREST(String search) throws Exception;

	Optional<Linea_detalle_pDto> findByIdREST(int id) throws Exception;

	Linea_detalle_pDto editarREST(Linea_detalle_pDto p) throws Exception;

	Linea_detalle_pDto saveREST(Linea_detalle_pDto p) throws Exception;

	Linea_detalle_pDto deleteREST(int id) throws Exception;
}
