package com.grupo2.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.grupo2.backend.entity.ProveedorEntity;

@Repository
public interface IProveedorRepository extends CrudRepository<ProveedorEntity, Integer> {

    @Query("SELECT p FROM ProveedorEntity p WHERE CONCAT(p.nombre_proveedor, p.categoria) LIKE %?1%")
    public List<ProveedorEntity> findAll(String search);


    @Query("SELECT pe.nota_conformidad FROM PedidoEntity pe " +
           "JOIN pe.linea_detalle_p ldp " +
           "JOIN ldp.producto prod " +
           "JOIN prod.prod_prov pp " +
           "JOIN pp.proveedor prov " +
           "WHERE prov.id = ?1")
    public List<Float> getNotasConformidadPedido(int id_proveedor);

}
