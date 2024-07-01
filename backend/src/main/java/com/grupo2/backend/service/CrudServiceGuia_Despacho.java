package com.grupo2.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.grupo2.backend.dto.EncargadoDto;
import com.grupo2.backend.dto.Guia_DespachoDto;
import com.grupo2.backend.entity.EncargadoEntity;
import com.grupo2.backend.entity.Guia_DespachoEntity;
import com.grupo2.backend.repository.IGuia_DespachoRepository;

@Component
@Service
public class CrudServiceGuia_Despacho implements ICrudServiceGuia_Despacho {
    @Autowired
    private IGuia_DespachoRepository guiaDespachoRepository;

    @Override
    public List<Guia_DespachoDto> findAll(String search) {
        List<Guia_DespachoEntity> listE;
        if (search != null) {
            listE = guiaDespachoRepository.findAll(search);
        } else {
            listE = (List<Guia_DespachoEntity>) guiaDespachoRepository.findAll();
        }

        List<Guia_DespachoDto> listDto = new ArrayList<>();
        for (Guia_DespachoEntity entity : listE) {
            Guia_DespachoDto dto = entity.toDto();
            if (entity.getEncargados() != null) {
                List<EncargadoDto> encargadosDto = entity.getEncargados().stream()
                        .map(EncargadoEntity::toDto)
                        .collect(Collectors.toList());
                dto.setEncargados(encargadosDto);
            }
            listDto.add(dto);
        }
        return listDto;
    }

    @Override
    public Optional<Guia_DespachoDto> findById(int id) {
        Optional<Guia_DespachoEntity> oe = guiaDespachoRepository.findById(id);
        if (oe.isPresent()) {
            Guia_DespachoDto dto = oe.get().toDto();
            if (oe.get().getEncargados() != null) {
                List<EncargadoDto> encargadosDto = oe.get().getEncargados().stream()
                        .map(EncargadoEntity::toDto)
                        .collect(Collectors.toList());
                dto.setEncargados(encargadosDto);
            }
            return Optional.of(dto);
        }
        return Optional.empty();
    }

    @Override
    public Guia_DespachoDto save(Guia_DespachoDto dto) {
        Guia_DespachoEntity entity = dto.toEntity();
        if (dto.getEncargados() != null) {
            List<EncargadoEntity> encargados = dto.getEncargados().stream()
                    .map(EncargadoDto::toEntity)
                    .collect(Collectors.toList());
            entity.setEncargados(encargados);
        }
        Guia_DespachoEntity savedEntity = guiaDespachoRepository.save(entity);
        Guia_DespachoDto savedDto = savedEntity.toDto();
        if (savedEntity.getEncargados() != null) {
            List<EncargadoDto> encargadosDto = savedEntity.getEncargados().stream()
                    .map(EncargadoEntity::toDto)
                    .collect(Collectors.toList());
            savedDto.setEncargados(encargadosDto);
        }
        return savedDto;
    }

    @Override
    public void delete(Guia_DespachoDto dto) {
        guiaDespachoRepository.delete(dto.toEntity());
    }
}


