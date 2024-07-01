package com.grupo2.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grupo2.backend.dto.Guia_DespachoDto;
import com.grupo2.backend.entity.Guia_DespachoEntity;
import com.grupo2.backend.repository.IGuia_DespachoRepository;

@Component
@Service
public class CrudServiceGuia_Despacho implements ICrudServiceGuia_Despacho {
    @Autowired
    private IGuia_DespachoRepository guiaDespachoRepository;

    @Override
    @Transactional(readOnly = true) // Asegúrate de que este método esté envuelto en una transacción
    public List<Guia_DespachoDto> findAll(String search) {
        List<Guia_DespachoEntity> listE;
        if (search != null) {
            listE = guiaDespachoRepository.findAll(search);
        } else {
            listE = (List<Guia_DespachoEntity>) guiaDespachoRepository.findAll();
        }

        List<Guia_DespachoDto> listDto = new ArrayList<>();
        for (Guia_DespachoEntity entity : listE) {
            listDto.add(entity.toDto());
        }
        return listDto;
    }

    @Override
    @Transactional(readOnly = true) // Asegúrate de que este método esté envuelto en una transacción
    public Optional<Guia_DespachoDto> findById(int id) {
        Optional<Guia_DespachoEntity> oe = guiaDespachoRepository.findById(id);
        if (oe.isPresent()) {
            return Optional.of(new Guia_DespachoDto(oe.get()));
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Guia_DespachoDto save(Guia_DespachoDto dto) {
        Guia_DespachoEntity entity = dto.toEntity();
        Guia_DespachoEntity savedEntity = guiaDespachoRepository.save(entity);
        return savedEntity.toDto();
    }

    @Override
    @Transactional
    public void delete(Guia_DespachoDto dto) {
        guiaDespachoRepository.delete(dto.toEntity());
    }
}
