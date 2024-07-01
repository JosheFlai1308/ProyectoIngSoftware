package com.grupo2.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.grupo2.backend.dto.EncargadoDto;
import com.grupo2.backend.entity.EncargadoEntity;
import com.grupo2.backend.entity.Guia_DespachoEntity;
import com.grupo2.backend.repository.IEncargadoRepository;
import com.grupo2.backend.repository.IGuia_DespachoRepository;


@Component
@Service
public class CrudServiceEncargado implements ICrudServiceEncargado {
    @Autowired
    private IEncargadoRepository encargadoRepository;

    @Autowired
    private IGuia_DespachoRepository guiaDespachoRepository;

    @Override
    public List<EncargadoDto> findAll(String search) {
        List<EncargadoEntity> listE;
        if (search != null) {
            listE = encargadoRepository.findAll(search);
        } else {
            listE = encargadoRepository.findAll();
        }

        List<EncargadoDto> listDto = new ArrayList<>();
        for (EncargadoEntity e : listE) {
            listDto.add(e.toDto());
        }
        return listDto;
    }

    @Override
    public Optional<EncargadoDto> findById(int id) {
        Optional<EncargadoEntity> oe = encargadoRepository.findById(id);
        if (oe.isPresent()) {
            return Optional.of(new EncargadoDto(oe.get()));
        }
        return Optional.empty();
    }

    @Override
    public EncargadoDto save(EncargadoDto dto) {
        EncargadoEntity entity = dto.toEntity();
        if (dto.getGuia_despacho() != null) {
            Guia_DespachoEntity guiaDespachoEntity = guiaDespachoRepository.save(dto.getGuia_despacho().toEntity());
            entity.setGuia_despacho(guiaDespachoEntity);
        }
        EncargadoEntity savedEntity = encargadoRepository.save(entity);
        return savedEntity.toDto();
    }

    @Override
    public void delete(EncargadoDto dto) {
        encargadoRepository.delete(dto.toEntity());
    }
}
