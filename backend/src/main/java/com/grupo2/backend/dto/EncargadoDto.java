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
    private String password;
    private Guia_DespachoDto guia_despacho;


    public EncargadoEntity toEntity (){
        EncargadoEntity e = new EncargadoEntity();
        e.setId(this.getId());
        e.setNombre_encargado(this.getNombre_encargado());
        e.setNumero_telefono(this.getNumero_telefono());
        e.setCorreo_electronico(this.getCorreo_electronico());
        e.setPassword(this.getPassword());
        if (this.getGuia_despacho() != null) {
            e.setGuia_despacho(this.getGuia_despacho().toEntity());
        }
        return e;
    }
    public EncargadoDto(EncargadoEntity encargado) {
        this.id = encargado.getId();
        this.nombre_encargado = encargado.getNombre_encargado();
        this.numero_telefono = encargado.getNumero_telefono();
        this.correo_electronico = encargado.getCorreo_electronico();
        this.password =  encargado.getPassword();
        if (encargado.getGuia_despacho() != null) {
            this.guia_despacho = new Guia_DespachoDto(encargado.getGuia_despacho());
        }
    }

    public static EncargadoDto fromEntity(EncargadoEntity encargado) {
        return new EncargadoDto(encargado);
    }

}
