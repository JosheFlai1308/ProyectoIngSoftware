package com.grupo2.backend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.grupo2.backend.entity.ProductoEntity;

@Repository
public interface IProductoRepository extends CrudRepository<ProductoEntity,Integer>{
    
}
