package com.gazelle.gazelle.Repositories;



import com.gazelle.gazelle.Models.CompraModel;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CompraRepository extends MongoRepository<CompraModel,String> {
    
}