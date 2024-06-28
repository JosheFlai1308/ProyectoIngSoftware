package com.grupo2.backend.service;

import java.util.List;
import java.util.Optional;

import com.grupo2.backend.dto.ConformidadDTO;

public interface ICrudServiceConformidad {

    List<ConformidadDTO> findAll(String search);

    Optional<ConformidadDTO> findById(int id);

    ConformidadDTO save(ConformidadDTO dto);

    void delete(ConformidadDTO dto);
}
