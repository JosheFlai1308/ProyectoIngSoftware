package com.grupo2.backend.dto;

import com.grupo2.backend.entity.EncargadoEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EncargadoDto {
        private int id;

    private String nameEncargado;


    public EncargadoEntity toEntity (){
        EncargadoEntity e = new EncargadoEntity();
        e.setId(this.getId());
        e.setNameEncargado(this.getNameEncargado());
        return e;
    }
}