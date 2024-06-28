package com.grupo2.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.grupo2.backend.dto.LineadetalleCDTO;
import com.grupo2.backend.entity.LineadetalleCEntity;
import com.grupo2.backend.repository.ILineadetalleCRepository;

@Component
@Service
public class CrudServiceLineadetalleC implements ICrudServiceLineadetalleC {

    @Autowired
    private ILineadetalleCRepository data;

    @Override
    public List<LineadetalleCDTO> findAll(String search) {
        List<LineadetalleCEntity> listE;
        if (search != null) {
            listE = data.findAll(search);
        } else {
            listE = (List<LineadetalleCEntity>) data.findAll();
        }
        List<LineadetalleCDTO> listDto = new ArrayList<>();
        for (LineadetalleCEntity e : listE) {
            listDto.add(e.toDto());
        }
        return listDto;
    }

    @Override
    public Optional<LineadetalleCDTO> findById(int id) {
        Optional<LineadetalleCEntity> oe = data.findById(id);
        if (oe.isPresent()) {
            LineadetalleCEntity e = oe.get();
            return Optional.ofNullable(e.toDto());
        }
        return Optional.empty();
    }

    @Override
    public LineadetalleCDTO save(LineadetalleCDTO dto) {
        LineadetalleCEntity e = data.save(dto.toEntity());
        return e.toDto();
    }

    @Override
    public void delete(LineadetalleCDTO dto) {
        data.delete(dto.toEntity());
    }
}
