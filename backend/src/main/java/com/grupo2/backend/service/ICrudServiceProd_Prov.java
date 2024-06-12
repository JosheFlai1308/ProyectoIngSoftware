package com.grupo2.backend.service;

import java.util.List;
import java.util.Optional;

import com.grupo2.backend.dto.Prod_ProvDto;

public interface ICrudServiceProd_Prov {

    public List<Prod_ProvDto> findAll(String search);

    public Optional<Prod_ProvDto> findById(int id);

    public Prod_ProvDto save(Prod_ProvDto dto);

    public void delete(Prod_ProvDto dto);

}
