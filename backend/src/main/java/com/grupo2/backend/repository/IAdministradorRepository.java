package com.grupo2.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.grupo2.backend.entity.AdministradorEntity;

@Repository
public interface IAdministradorRepository extends CrudRepository<AdministradorEntity, Integer> {
    @Query("SELECT a FROM AdministradorEntity a WHERE CONCAT(a.nombre) LIKE %?1%")
    public List<AdministradorEntity> findAll(String search);
}
