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

    private String nombre_encargado;
    private String numero_telefono;
    private String correo_electronico;


    public EncargadoEntity toEntity (){
        EncargadoEntity e = new EncargadoEntity();
        e.setId(this.getId());
        e.setNombre_encargado(this.getNombre_encargado());
        e.setNumero_telefono(this.getNumero_telefono());
        e.setCorreo_electronico(this.getCorreo_electronico());
        return e;
    }
    public EncargadoDto(EncargadoEntity encargado) {
        this.id = encargado.getId();
        this.nombre_encargado = encargado.getNombre_encargado();
    }

    public static EncargadoDto fromEntity(EncargadoEntity encargado) {
        return new EncargadoDto(encargado);
    }

}
