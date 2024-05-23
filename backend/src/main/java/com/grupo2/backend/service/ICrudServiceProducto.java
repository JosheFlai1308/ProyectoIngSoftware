package com.grupo2.backend.service;

import java.util.List;
import java.util.Optional;

import com.grupo2.backend.dto.ProductoDto;


public interface ICrudServiceProducto {

    public List<ProductoDto> findAll(String key );
    public Optional<ProductoDto> findById(int id);
    public ProductoDto save(ProductoDto dto);
    public void delete(ProductoDto dto);
}