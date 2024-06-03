package com.grupo2.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.grupo2.backend.dto.ProductoDto;
import com.grupo2.backend.entity.ProductoEntity;
import com.grupo2.backend.repository.IProductoRepository;

@Component
@Service
public class CrudServiceProducto implements ICrudServiceProducto {

	@Autowired
	private IProductoRepository data;

	@Override
	public List<ProductoDto> findAll(String search) {
		if (search != null) {
			List<ProductoEntity> listE = (List<ProductoEntity>) data.findAll(search);
			List<ProductoDto> listDto = new ArrayList<>();
			for (ProductoEntity e : listE) {
				listDto.add(e.toDto());
			}
			return listDto;
		}

		List<ProductoEntity> listE = (List<ProductoEntity>) data.findAll();
		List<ProductoDto> listDto = new ArrayList<>();
		for (ProductoEntity e : listE) {
			listDto.add(e.toDto());
		}
		return listDto;
	}

	@Override
	public Optional<ProductoDto> findById(int id) {
		Optional<ProductoEntity> oe = data.findById(id);
		ProductoEntity e = oe.get();
		ProductoDto dto = e.toDto();
		return Optional.ofNullable(dto);
	}

	@Override
	public ProductoDto save(ProductoDto dto) {
		ProductoEntity e = data.save(dto.toEntity());
		return e.toDto();
	}

	@Override
	public void delete(ProductoDto dto) {
		data.delete(dto.toEntity());
	}

}
