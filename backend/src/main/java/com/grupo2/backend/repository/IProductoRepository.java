package com.grupo2.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.grupo2.backend.entity.ProductoEntity;

@Repository
public interface IProductoRepository extends CrudRepository<ProductoEntity, Integer> {
    @Query("SELECT p FROM ProductoEntity p WHERE CONCAT(p.nombre_producto) LIKE %?1%")
    public List<ProductoEntity> findAll(String search);
}
