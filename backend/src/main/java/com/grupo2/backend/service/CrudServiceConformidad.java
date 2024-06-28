package com.grupo2.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo2.backend.dto.ConformidadDTO;
import com.grupo2.backend.entity.ConformidadEntity;
import com.grupo2.backend.repository.IConformidadRepository;

@Service
public class CrudServiceConformidad implements ICrudServiceConformidad {

    @Autowired
    private IConformidadRepository data;

    @Override
   public List<ConformidadDTO> findAll(String search) {
		if (search != null) {
			List<ConformidadEntity> listC = (List<ConformidadEntity>) data.findAll(search);
			List<ConformidadDTO> listDto = new ArrayList<>();
			for (ConformidadEntity c : listC) {
				listDto.add(c.toDto());
			}
			return listDto;
		}

		List<ConformidadEntity> listC = (List<ConformidadEntity>) data.findAll();
		List<ConformidadDTO> listDto = new ArrayList<>();
		for (ConformidadEntity c : listC) {
			listDto.add(c.toDto());
		}
		return listDto;
	}

    @Override
    public Optional<ConformidadDTO> findById(int id) {
        Optional<ConformidadEntity> oc = data.findById(id);
        if (oc.isPresent()) {
            ConformidadEntity c = oc.get();
            ConformidadDTO dto = c.toDto();
            return Optional.ofNullable(dto);
        }
        return Optional.empty();
    }

    @Override
    public ConformidadDTO save(ConformidadDTO dto) {
        ConformidadEntity c = data.save(dto.toEntity());
        return c.toDto();
    }

    @Override
    public void delete(ConformidadDTO dto) {
        data.delete(dto.toEntity());
    }
}
