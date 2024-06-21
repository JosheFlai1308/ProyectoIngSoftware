package com.grupo2.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.grupo2.backend.entity.PedidoEntity;

public interface IPedidoRepository extends CrudRepository<PedidoEntity, Integer> {
    @Query("SELECT p FROM PedidoEntity p WHERE CONCAT(p.estado) LIKE %?1%")
    public List<PedidoEntity> findAll(String search);
}
