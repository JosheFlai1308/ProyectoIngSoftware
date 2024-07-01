package com.grupo2.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.grupo2.backend.dto.ProveedorDto;
import com.grupo2.backend.entity.ProveedorEntity;
import com.grupo2.backend.repository.IProveedorRepository;

@Component
@Service
public class CrudServiceProveedor implements ICrudServiceProveedor{

    @Autowired
	private IProveedorRepository data;

	@Override
	public List<Float> getNotasConformidadPedido(int id_proveedor) {
        return data.getNotasConformidadPedido(id_proveedor);
	}
	
	@Override
	public List<ProveedorDto> findAll(String search) {
		if (search != null) {
			List<ProveedorEntity> listE = (List<ProveedorEntity>) data.findAll(search);
			List<ProveedorDto> listDto = new ArrayList<>();
			for (ProveedorEntity e : listE) {
				listDto.add(e.toDto());
			}
			return listDto;
		}

		List<ProveedorEntity> listE = (List<ProveedorEntity>) data.findAll();
		List<ProveedorDto> listDto = new ArrayList<>();
		for (ProveedorEntity e : listE) {
			listDto.add(e.toDto());
		}
		return listDto;
	}

	@Override
	public Optional<ProveedorDto> findById(int id) {
		Optional<ProveedorEntity> oe = data.findById(id);
		ProveedorEntity e = oe.get();
		ProveedorDto dto = e.toDto();
		return Optional.ofNullable(dto);
	}

	@Override
	public ProveedorDto save(ProveedorDto dto) {
		ProveedorEntity e = data.save(dto.toEntity());
		return e.toDto();
	}

	@Override
	public void delete(ProveedorDto dto) {
		data.delete(dto.toEntity());
	}
}
