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
    private String nombreEncargado;

    @NotBlank
    @Column(name = "numero_telefono")
    private String numeroTelefono;

    @NotBlank
    @Column(name = "correo_electronico")
    private String correoElectronico;

    public EncargadoEntity (@JsonProperty("id") int id,
                    @JsonProperty("nombre_encargado") String nombreEncargado,@JsonProperty("numero_telefono")  String numeroTelefono,
                    @JsonProperty("correo_electronico") String correoElectronico ){
        super();
        this.id = id;
        this.nombreEncargado = nombreEncargado;
        this.numeroTelefono = numeroTelefono;
        this.correoElectronico = correoElectronico;
    }

    public EncargadoDto toDto(){
    EncargadoDto dto = new EncargadoDto();
    dto.setId(this.getId());
    dto.setNombreEncargado(this.getNombreEncargado());
    dto.setNumeroTelefono(this.getNumeroTelefono());
    dto.setCorreoElectronico(this.getCorreoElectronico());
    return dto;
    }

}
