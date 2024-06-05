package com.grupo2.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.grupo2.backend.dto.EncargadoDto;
import com.grupo2.backend.entity.EncargadoEntity;
import com.grupo2.backend.repository.IEncargadoRepository;


@Component
@Service
public class CrudServiceEncargado implements ICrudServiceEncargado{
    @Autowired
	private IEncargadoRepository data;

	@Override
	public List<EncargadoDto> findAll(String search) {
		if (search != null) {
			List<EncargadoEntity> listE = (List<EncargadoEntity>) data.findAll(search);
			List<EncargadoDto> listDto = new ArrayList<>();
			for (EncargadoEntity e : listE) {
				listDto.add(e.toDto());
			}
			return listDto;
		}

		List<EncargadoEntity> listE = (List<EncargadoEntity>) data.findAll();
		List<EncargadoDto> listDto = new ArrayList<>();
		for (EncargadoEntity e : listE) {
			listDto.add(e.toDto());
		}
		return listDto;
	}

	@Override
	public Optional<EncargadoDto> findById(int id) {
		Optional<EncargadoEntity> oe = data.findById(id);
		EncargadoEntity e = oe.get();
		EncargadoDto dto = e.toDto();
		return Optional.ofNullable(dto);
	}

	@Override
	public EncargadoDto save(EncargadoDto dto) {
		EncargadoEntity e = data.save(dto.toEntity());
		return e.toDto();
	}

	@Override
	public void delete(EncargadoDto dto) {
		data.delete(dto.toEntity());
	}
}
