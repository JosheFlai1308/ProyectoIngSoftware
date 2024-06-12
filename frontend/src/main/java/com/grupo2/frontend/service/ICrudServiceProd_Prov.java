package com.grupo2.frontend.service;

import java.util.List;
import java.util.Optional;

import com.grupo2.frontend.dto.Prod_ProvDto;

public interface ICrudServiceProd_Prov {

    public List<Prod_ProvDto> findAllREST(String search) throws Exception;

	public Optional<Prod_ProvDto> findByIdREST(int id) throws Exception;

	public Prod_ProvDto editarREST(Prod_ProvDto dto) throws Exception;

	public Prod_ProvDto saveREST(Prod_ProvDto dto) throws Exception;

	public Prod_ProvDto deleteREST(int id) throws Exception;
}
