package com.grupo2.backend.service;

import java.util.List;
import java.util.Optional;

import com.grupo2.backend.dto.Linea_detalle_pDto;

public interface ICrudServiceLinea_detalle_p {

    public List<Linea_detalle_pDto> findAll(String search);

    public Optional<Linea_detalle_pDto> findById(int id);

    public Linea_detalle_pDto save(Linea_detalle_pDto dto);

    public void delete(Linea_detalle_pDto dto);
}
