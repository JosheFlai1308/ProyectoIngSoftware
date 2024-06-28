package com.grupo2.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.grupo2.backend.entity.LineadetalleCEntity;

public interface ILineadetalleCRepository extends CrudRepository<LineadetalleCEntity, Integer> {
    @Query("SELECT l FROM LineadetalleC l WHERE CONCAT(l.criterio) LIKE %?1%")
    public List<LineadetalleCEntity> findAll(String search);
}
