package com.grupo2.backend.service;

import java.util.List;
import java.util.Optional;

import com.grupo2.backend.dto.EncargadoDto;

public interface ICrudServiceEncargado {
    public List<EncargadoDto> findAll(String search);

    public Optional<EncargadoDto> findById(int id);

    public EncargadoDto save(EncargadoDto dto);

    public void delete(EncargadoDto dto);
}
