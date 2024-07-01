package com.grupo2.backend.service;

import java.util.List;
import java.util.Optional;

import com.grupo2.backend.dto.ProveedorDto;

public interface ICrudServiceProveedor {

    public List<Float> getNotasConformidad(int id_proveedor);

    public List<ProveedorDto> findAll(String search);

    public Optional<ProveedorDto> findById(int id);

    public ProveedorDto save(ProveedorDto dto);

    public void delete(ProveedorDto dto);
}
