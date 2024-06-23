package com.grupo2.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo2.backend.dto.PedidoDto;
import com.grupo2.backend.entity.PedidoEntity;
import com.grupo2.backend.repository.IPedidoRepository;

@Service
public class CrudServicePedido implements ICrudServicePedido {

    @Autowired
    private IPedidoRepository data;

    @Override
   public List<PedidoDto> findAll(String search) {
		if (search != null) {
			List<PedidoEntity> listE = (List<PedidoEntity>) data.findAll(search);
			List<PedidoDto> listDto = new ArrayList<>();
			for (PedidoEntity e : listE) {
				listDto.add(e.toDto());
			}
			return listDto;
		}

		List<PedidoEntity> listE = (List<PedidoEntity>) data.findAll();
		List<PedidoDto> listDto = new ArrayList<>();
		for (PedidoEntity e : listE) {
			listDto.add(e.toDto());
		}
		return listDto;
	}

    @Override
    public Optional<PedidoDto> findById(int id) {
        Optional<PedidoEntity> oe = data.findById(id);
        if (oe.isPresent()) {
            PedidoEntity e = oe.get();
            PedidoDto dto = e.toDto();
            return Optional.ofNullable(dto);
        }
        return Optional.empty();
    }

    @Override
    public PedidoDto save(PedidoDto dto) {
        PedidoEntity e = data.save(dto.toEntity());
        return e.toDto();
    }

    @Override
    public void delete(PedidoDto dto) {
        data.delete(dto.toEntity());
    }
}
