package com.grupo2.backend.entity;

import java.util.List;
import java.sql.Date;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.grupo2.backend.dto.ConformidadDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "conformidad")
public class ConformidadEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_conformidad;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "conformidad")
    private List<LineadetalleCEntity> linea_detalle_c;

    //Falta relacion con admin Y preguntar a chatgpt si hay que modificar los demás codigos 

    public ConformidadEntity(@JsonProperty("id_conformidad") int id_conformidad){
        super();
        this.id_conformidad = id_conformidad;
    
    }

    public ConformidadDTO toDto() {
        ConformidadDTO dto = new ConformidadDTO();
        dto.setId_conformidad(this.getId_conformidad());
        return dto;
    }
}
