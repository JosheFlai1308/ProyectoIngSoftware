package com.grupo2.frontend.service;

import java.util.List;
import java.util.Optional;

import com.grupo2.frontend.dto.ProductoDto;

public interface ICrudServiceProducto {

	public List<ProductoDto> findAllREST(String search) throws Exception;

	public Optional<ProductoDto> findByIdREST(int id) throws Exception;

	public ProductoDto editarREST(ProductoDto dto) throws Exception;

	public ProductoDto saveREST(ProductoDto dto) throws Exception;

	public ProductoDto deleteREST(int id) throws Exception;
}
