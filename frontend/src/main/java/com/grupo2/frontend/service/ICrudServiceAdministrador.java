package com.grupo2.frontend.service;

import java.util.List;
import java.util.Optional;

import com.grupo2.frontend.dto.AdministradorDTO;

public interface ICrudServiceAdministrador {
    public List<AdministradorDTO> findAllREST(String search) throws Exception;

	public Optional<AdministradorDTO> findByIdREST(int id) throws Exception;

	public AdministradorDTO editarREST(AdministradorDTO dto) throws Exception;

	public AdministradorDTO saveREST(AdministradorDTO dto) throws Exception;

	public AdministradorDTO deleteREST(int id) throws Exception;
}
