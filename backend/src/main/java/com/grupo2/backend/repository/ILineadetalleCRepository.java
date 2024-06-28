package com.grupo2.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.grupo2.backend.entity.LineadetalleCEntity;

public interface ILineadetalleCRepository extends CrudRepository<LineadetalleCEntity, Integer> {
    @Query("SELECT l FROM LineadetalleCEntity l WHERE l.criterio LIKE %:search%")
public List<LineadetalleCEntity> findAll(@Param("search") String search);
}
