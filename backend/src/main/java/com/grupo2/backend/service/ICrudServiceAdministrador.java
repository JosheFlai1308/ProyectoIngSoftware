package com.grupo2.backend.service;

import java.util.List;
import java.util.Optional;

import com.grupo2.backend.dto.AdministradorDTO;

public interface ICrudServiceAdministrador {
    public List<AdministradorDTO> findAll(String search);

    public Optional<AdministradorDTO> findById(int id);

    public AdministradorDTO save(AdministradorDTO dto);

    public void delete(AdministradorDTO dto);
}
