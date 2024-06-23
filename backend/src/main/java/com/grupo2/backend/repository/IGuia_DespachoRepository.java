package com.grupo2.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.grupo2.backend.entity.Guia_DespachoEntity;

@Repository
public interface IGuia_DespachoRepository extends CrudRepository<Guia_DespachoEntity, Integer> {
    @Query("SELECT e FROM Guia_DespachoEntity e WHERE CONCAT(e.fecha) LIKE %?1%")
    public List<Guia_DespachoEntity> findAll(String search);
}
