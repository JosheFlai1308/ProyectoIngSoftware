package com.grupo2.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.grupo2.backend.entity.Linea_detalle_p;

public interface ILinea_detalle_pRepository extends CrudRepository<Linea_detalle_p, Integer> {
    @Query("SELECT l FROM Linea_detalle_p l WHERE CONCAT(l.cantidad, l.subtotal, l.producto.nombre_producto, l.pedido.id_pedido) LIKE %?1%")
    public List<Linea_detalle_p> findAll(String search);
}
