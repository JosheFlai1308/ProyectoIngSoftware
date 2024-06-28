package com.grupo2.backend.dto;

import com.grupo2.backend.entity.ConformidadEntity;
import com.grupo2.backend.entity.LineadetalleCEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineadetalleCDTO {

    private int id_linea_detallec;
    private String criterio;
    private int pje_criterio;
    private ConformidadEntity conformidad;

    public LineadetalleCEntity toEntity() {
        LineadetalleCEntity l = new LineadetalleCEntity();
        l.setId_linea_detallec(this.getId_linea_detallec());
        l.setCriterio(this.getCriterio());
        l.setPje_criterio(this.getPje_criterio());
        l.setConformidad(this.getConformidad());
        return l;
    }

    public static LineadetalleCDTO fromEntity(LineadetalleCEntity entity) {
        LineadetalleCDTO dto = new LineadetalleCDTO();
        dto.setId_linea_detallec(entity.getId_linea_detallec());
        dto.setCriterio(entity.getCriterio());
        dto.setPje_criterio(entity.getPje_criterio());
        dto.setConformidad(entity.getConformidad());
        return dto;
    }
}
