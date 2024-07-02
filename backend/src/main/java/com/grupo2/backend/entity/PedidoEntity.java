package com.grupo2.backend.entity;

import java.util.List;
import java.sql.Date;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.grupo2.backend.dto.PedidoDto;

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
@Table(name = "pedido")
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_pedido;

    @Column(name = "total")
    private int total;

    @Column(name = "nota_conformidad")
    private String nota_conformidad;

    @Column(name = "fecha_emitido")
    private Date fecha_emitido;

    @Column(name = "fecha_entrega")
    private Date fecha_entrega;

    @Column(name = "estado")
    private String estado;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedido")
    private List<Linea_detalle_p> linea_detalle_p;

    public PedidoEntity(@JsonProperty("id_pedido") int id_pedido,
            @JsonProperty("total") int total,
            @JsonProperty("nota_conformidad") String nota_conformidad,
            @JsonProperty("fecha_emitido") Date fecha_emitido,
            @JsonProperty("fecha_entrega") Date fecha_entrega,
            @JsonProperty("estado") String estado) {
        super();
        this.id_pedido = id_pedido;
        this.total = total;
        this.nota_conformidad = nota_conformidad;
        this.fecha_emitido = fecha_emitido;
        this.fecha_entrega = fecha_entrega;
        this.estado = estado;

    }

    public PedidoDto toDto() {
        PedidoDto dto = new PedidoDto();
        dto.setId_pedido(this.getId_pedido());
        dto.setTotal(this.getTotal());
        dto.setNota_conformidad(this.getNota_conformidad());
        dto.setFecha_emitido(this.getFecha_emitido());
        dto.setFecha_entrega(this.getFecha_entrega());
        dto.setEstado(this.getEstado());
        return dto;
    }
}
