package com.grupo2.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.grupo2.backend.entity.ConformidadEntity;

public interface IConformidadRepository extends CrudRepository<ConformidadEntity, Integer> {
    @Query("SELECT c FROM ConformidadEntity c WHERE CONCAT(c.id_conformidad) LIKE %?1%")
    public List<ConformidadEntity> findAll(String search);
}
