package com.gazelle.gazelle.Repositories;


import java.util.Optional;

import com.gazelle.gazelle.Models.ProductoModel;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductoRepository extends MongoRepository<ProductoModel,String> {
    public Optional<ProductoModel> findByNombreProducto(String nombreProducto);


}