package com.grupo2.backend.service;

import java.util.List;
import java.util.Optional;

import com.grupo2.backend.dto.Guia_DespachoDto;

public interface ICrudServiceGuia_Despacho{
        public List<Guia_DespachoDto> findAll(String search);

    public Optional<Guia_DespachoDto> findById(int id);

    public Guia_DespachoDto save(Guia_DespachoDto dto);

    public void delete(Guia_DespachoDto dto);
}
