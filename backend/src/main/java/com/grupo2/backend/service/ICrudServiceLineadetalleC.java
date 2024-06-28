package com.grupo2.backend.service;

import java.util.List;
import java.util.Optional;

import com.grupo2.backend.dto.LineadetalleCDTO;

public interface ICrudServiceLineadetalleC {

    List<LineadetalleCDTO> findAll(String search);

    Optional<LineadetalleCDTO> findById(int id);

    LineadetalleCDTO save(LineadetalleCDTO dto);

    void delete(LineadetalleCDTO dto);
}
