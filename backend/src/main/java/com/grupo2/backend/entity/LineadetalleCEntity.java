package com.grupo2.backend.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.grupo2.backend.dto.LineadetalleCDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "LineadetalleC")
public class LineadetalleCEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_linea_detallec;

    @Column(name = "criterio")
    private String criterio;

    @Column(name = "pje_criterio")
    private int pje_criterio;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_conformidad", nullable = false)
    private ConformidadEntity conformidad;

    public LineadetalleCEntity(@JsonProperty("id_linea_detallec") int id_linea_detallec,
            @JsonProperty("criterio") String criterio,
            @JsonProperty("pje_criterio") int pje_criterio,
            @JsonProperty("id_conformidad") ConformidadEntity conformidad) {
        super();
        this.id_linea_detallec = id_linea_detallec;
        this.criterio = criterio;
        this.pje_criterio = pje_criterio;
        this.conformidad = conformidad;
    }

    public LineadetalleCDTO toDto() {
        LineadetalleCDTO dto = new LineadetalleCDTO();
        dto.setId_linea_detallec(this.getId_linea_detallec());
        dto.setCriterio(this.getCriterio());
        dto.setPje_criterio(this.getPje_criterio());
        dto.setConformidad(this.getConformidad());
        return dto;
    }
}
