package com.grupo2.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.grupo2.backend.entity.EncargadoEntity;

@Repository
public interface IEncargadoRepository extends JpaRepository<EncargadoEntity, Integer>  {

    @Query("SELECT e FROM EncargadoEntity e WHERE CONCAT(e.nombre_encargado) LIKE %?1%")
    public List<EncargadoEntity> findAll(String search);
}