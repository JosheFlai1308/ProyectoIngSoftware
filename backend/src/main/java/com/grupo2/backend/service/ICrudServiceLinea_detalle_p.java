package com.grupo2.backend.service;

import java.util.List;
import java.util.Optional;

import com.grupo2.backend.dto.Linea_detalle_pDto;

public interface ICrudServiceLinea_detalle_p {

    List<Linea_detalle_pDto> findAll(String search);

    Optional<Linea_detalle_pDto> findById(int id);

    Linea_detalle_pDto save(Linea_detalle_pDto dto);

    void delete(Linea_detalle_pDto dto);
}
