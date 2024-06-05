package com.grupo2.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.grupo2.backend.entity.EncargadoEntity;

@Repository
public interface IEncargadoRepository extends CrudRepository<EncargadoEntity, Integer> {
    @Query("SELECT e FROM EncargadoEntity e WHERE CONCAT(e.nombre) LIKE %?1%")
    public List<EncargadoEntity> findAll(String search);
}
