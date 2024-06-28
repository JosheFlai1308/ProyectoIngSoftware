package com.grupo2.backend.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.grupo2.backend.dto.AdministradorDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "administrador")
public class AdministradorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Column(name = "nombre")
    private String nombre;

    @NotBlank
    @Column(name = "numero_telefono")
    private String numero_telefono;

    @NotBlank
    @Column(name = "correo_electronico")
    private String correo_electronico;

    //Revisar si es correcto conformidad_id o id_conformidad
    @ManyToOne
    @JoinColumn(name = "conformidad_id")
    private ConformidadEntity conformidad;

    public AdministradorEntity (@JsonProperty("id") int id,
                    @JsonProperty("nombre") String nombre,@JsonProperty("numero_telefono")  String numero_telefono,
                    @JsonProperty("correo_electronico") String correo_electronico){
        super();
        this.id = id;
        this.nombre = nombre;
        this.numero_telefono = numero_telefono;
        this.correo_electronico = correo_electronico;
    }

    public AdministradorDTO toDto(){
    AdministradorDTO dto = new AdministradorDTO();
    dto.setId(this.getId());
    dto.setNombre(this.getNombre());
    dto.setNumero_telefono(this.getNumero_telefono());
    dto.setCorreo_electronico(this.getCorreo_electronico());
    return dto;
    }

}

