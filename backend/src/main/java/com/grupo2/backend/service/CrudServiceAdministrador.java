package com.grupo2.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.grupo2.backend.dto.AdministradorDTO;
import com.grupo2.backend.entity.AdministradorEntity;
import com.grupo2.backend.repository.IAdministradorRepository;


@Component
@Service
public class CrudServiceAdministrador implements ICrudServiceAdministrador{
    @Autowired
	private IAdministradorRepository data;

	@Override
	public List<AdministradorDTO> findAll(String search) {
		if (search != null) {
			List<AdministradorEntity> listA = (List<AdministradorEntity>) data.findAll(search);
			List<AdministradorDTO> listDto = new ArrayList<>();
			for (AdministradorEntity a : listA) {
				listDto.add(a.toDto());
			}
			return listDto;
		}

		List<AdministradorEntity> listA = (List<AdministradorEntity>) data.findAll();
		List<AdministradorDTO> listDto = new ArrayList<>();
		for (AdministradorEntity a : listA) {
			listDto.add(a.toDto());
		}
		return listDto;
	}

	@Override
	public Optional<AdministradorDTO> findById(int id) {
		Optional<AdministradorEntity> oa = data.findById(id);
		AdministradorEntity a = oa.get();
		AdministradorDTO dto = a.toDto();
		return Optional.ofNullable(dto);
	}

	@Override
	public AdministradorDTO save(AdministradorDTO dto) {
		AdministradorEntity a = data.save(dto.toEntity());
		return a.toDto();
	}

	@Override
	public void delete(AdministradorDTO dto) {
		data.delete(dto.toEntity());
	}
}
