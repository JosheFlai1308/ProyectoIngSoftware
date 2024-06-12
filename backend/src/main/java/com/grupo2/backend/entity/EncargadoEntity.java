package com.grupo2.backend.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.grupo2.backend.dto.EncargadoDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "encargado")
public class EncargadoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Column(name = "nombre_encargado")
    private String nombre_encargado;

    @NotBlank
    @Column(name = "numero_telefono")
    private String numero_telefono;

    @NotBlank
    @Column(name = "correo_electronico")
    private String correo_electronico;

    public EncargadoEntity (@JsonProperty("id") int id,
                    @JsonProperty("nombre_encargado") String nombre_encargado,@JsonProperty("numero_telefono")  String numero_telefono,
                    @JsonProperty("correo_electronico") String correo_electronico ){
        super();
        this.id = id;
        this.nombre_encargado = nombre_encargado;
        this.numero_telefono = numero_telefono;
        this.correo_electronico = correo_electronico;
    }

    public EncargadoDto toDto(){
    EncargadoDto dto = new EncargadoDto();
    dto.setId(this.getId());
    dto.setNombre_encargado(this.getNombre_encargado());
    dto.setNumero_telefono(this.getNumero_telefono());
    dto.setCorreo_electronico(this.getCorreo_electronico());
    return dto;
    }

}
