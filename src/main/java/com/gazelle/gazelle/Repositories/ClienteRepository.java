package com.gazelle.gazelle.Repositories;


import java.util.Optional;

import com.gazelle.gazelle.Models.ClienteModel;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ClienteRepository extends MongoRepository<ClienteModel,String> {
    public Optional<ClienteModel> findByNick(String nick);


}