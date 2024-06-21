package com.grupo2.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.grupo2.backend.dto.Linea_detalle_pDto;
import com.grupo2.backend.entity.Linea_detalle_p;
import com.grupo2.backend.repository.ILinea_detalle_pRepository;

@Component
@Service
public class CrudServiceLinea_detalle_p implements ICrudServiceLinea_detalle_p {

    @Autowired
    private ILinea_detalle_pRepository data;

    @Override
    public List<Linea_detalle_pDto> findAll(String search) {
        List<Linea_detalle_p> listE;
        if (search != null) {
            listE = data.findAll(search);
        } else {
            listE = (List<Linea_detalle_p>) data.findAll();
        }
        List<Linea_detalle_pDto> listDto = new ArrayList<>();
        for (Linea_detalle_p e : listE) {
            listDto.add(e.toDto());
        }
        return listDto;
    }

    @Override
    public Optional<Linea_detalle_pDto> findById(int id) {
        Optional<Linea_detalle_p> oe = data.findById(id);
        if (oe.isPresent()) {
            Linea_detalle_p e = oe.get();
            return Optional.ofNullable(e.toDto());
        }
        return Optional.empty();
    }

    @Override
    public Linea_detalle_pDto save(Linea_detalle_pDto dto) {
        Linea_detalle_p e = data.save(dto.toEntity());
        return e.toDto();
    }

    @Override
    public void delete(Linea_detalle_pDto dto) {
        data.delete(dto.toEntity());
    }
}
