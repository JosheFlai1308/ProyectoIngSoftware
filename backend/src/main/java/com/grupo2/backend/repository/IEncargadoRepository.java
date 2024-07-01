package com.grupo2.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.grupo2.backend.entity.EncargadoEntity;

@Repository
public interface IEncargadoRepository extends JpaRepository<EncargadoEntity, Integer>  {


    @Query("SELECT e FROM EncargadoEntity e WHERE CONCAT(e.nombre_encargado) LIKE %?1%")
    public List<EncargadoEntity> findAll(String search);

    @Query("SELECT e FROM EncargadoEntity e WHERE e.correo_electronico = ?1 AND e.password = ?2")
    EncargadoEntity findByCorreo_electronicoAndPassword(String correo_electronico, String password);

    


}