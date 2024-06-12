package com.grupo2.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.grupo2.backend.dto.Prod_ProvDto;
import com.grupo2.backend.entity.Prod_Prov;
import com.grupo2.backend.repository.IProd_ProvRepository;

@Component
@Service
public class CrudServiceProd_Prov implements ICrudServiceProd_Prov{

    @Autowired
	private IProd_ProvRepository data;

	@Override
	public List<Prod_ProvDto> findAll(String search) {
		if (search != null) {
			List<Prod_Prov> listE = (List<Prod_Prov>) data.findAll(search);
			List<Prod_ProvDto> listDto = new ArrayList<>();
			for (Prod_Prov pp: listE) {
				listDto.add(pp.toDto());
			}
			return listDto;
		}

		List<Prod_Prov> listE = (List<Prod_Prov>) data.findAll();
		List<Prod_ProvDto> listDto = new ArrayList<>();
		for (Prod_Prov pp: listE) {
			listDto.add(pp.toDto());
		}
		return listDto;
	}

	@Override
	public Optional<Prod_ProvDto> findById(int id) {
		Optional<Prod_Prov> oe = data.findById(id);
		Prod_Prov pp= oe.get();
		Prod_ProvDto dto = pp.toDto();
		return Optional.ofNullable(dto);
	}

	@Override
	public Prod_ProvDto save(Prod_ProvDto dto) {
		Prod_Prov pp= data.save(dto.toEntity());
		return pp.toDto();
	}

	@Override
	public void delete(Prod_ProvDto dto) {
		data.delete(dto.toEntity());
	}

}
