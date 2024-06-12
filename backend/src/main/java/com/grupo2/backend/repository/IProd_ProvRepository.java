package com.grupo2.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.grupo2.backend.entity.Prod_Prov;

public interface IProd_ProvRepository extends CrudRepository<Prod_Prov, Integer> {
    @Query("SELECT p FROM Prod_Prov p WHERE CONCAT(p.precio, p.producto.nombre_producto, p.proveedor.nombre_proveedor) LIKE %?1%")
    public List<Prod_Prov> findAll(String search);

}
