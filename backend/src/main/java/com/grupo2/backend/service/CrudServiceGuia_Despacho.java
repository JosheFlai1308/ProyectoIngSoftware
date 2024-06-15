package com.grupo2.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.grupo2.backend.dto.Guia_DespachoDto;
import com.grupo2.backend.entity.Guia_DespachoEntity;
import com.grupo2.backend.repository.IGuia_DespachoRepository;

@Component
@Service
public class CrudServiceGuia_Despacho implements ICrudServiceGuia_Despacho {

    @Autowired
    private IGuia_DespachoRepository data;

    @Override
    public List<Guia_DespachoDto> findAll(String search) {
        List<Guia_DespachoEntity> listE;
        if (search != null) {
            listE = data.findAll(search);
        } else {
            listE = (List<Guia_DespachoEntity>) data.findAll();
        }
        List<Guia_DespachoDto> listDto = new ArrayList<>();
        for (Guia_DespachoEntity pp : listE) {
            listDto.add(pp.toDto());
        }
        return listDto;
    }

    @Override
    public Optional<Guia_DespachoDto> findById(int id) {
        Optional<Guia_DespachoEntity> oe = data.findById(id);
        return oe.map(Guia_DespachoEntity::toDto);
    }

    @Override
    public Guia_DespachoDto save(Guia_DespachoDto dto) {
        Guia_DespachoEntity pp = data.save(dto.toEntity());
        return pp.toDto();
    }

    @Override
    public void delete(Guia_DespachoDto dto) {
        data.delete(dto.toEntity());
    }
}


