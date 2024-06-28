package com.grupo2.backend.dto;

import com.grupo2.backend.entity.AdministradorEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdministradorDTO {
    private int id;

    private String nombre;
    private String numero_telefono;
    private String correo_electronico;

    public AdministradorEntity toEntity (){
        AdministradorEntity a = new AdministradorEntity();
        a.setId(this.getId());
        a.setNombre(this.getNombre());
        a.setNumero_telefono(this.getNumero_telefono());
        a.setCorreo_electronico(this.getCorreo_electronico());
        return a;
    }
    public AdministradorDTO(AdministradorEntity administrador) {
        this.id = administrador.getId();
        this.nombre = administrador.getNombre();
        this.numero_telefono = administrador.getNumero_telefono();
        this.correo_electronico = administrador.getCorreo_electronico();
    }

    public static AdministradorDTO fromEntity(AdministradorEntity administrador) {
        return new AdministradorDTO(administrador);
    }

}
