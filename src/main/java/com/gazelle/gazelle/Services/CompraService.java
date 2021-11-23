package com.gazelle.gazelle.Services;

import java.util.List;
import com.gazelle.gazelle.Models.CompraModel;
import com.gazelle.gazelle.Repositories.CompraRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompraService {
    
    @Autowired
    CompraRepository compraRepository;


    public void guardarCompra(CompraModel compra){
        this.compraRepository.save(compra);
    }

    public List<CompraModel> traerTodos(){
        return this.compraRepository.findAll();

    }

}
