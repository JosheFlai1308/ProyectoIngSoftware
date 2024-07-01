package com.grupo2.frontend.service;

import java.util.List;
import java.util.Optional;

import com.grupo2.frontend.dto.ProveedorDto;

public interface ICrudServiceProveedor {

    public List<Float> getNotasConformidad(int id_proveedor);

    public List<ProveedorDto> findAllREST(String search) throws Exception;

    public Optional<ProveedorDto> findByIdREST(int id) throws Exception;

    public ProveedorDto editarREST(ProveedorDto dto) throws Exception;

    public ProveedorDto saveREST(ProveedorDto dto) throws Exception;

    public ProveedorDto deleteREST(int id) throws Exception;
}
