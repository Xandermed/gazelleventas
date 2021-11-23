package com.gazelle.gazelle.Services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.gazelle.gazelle.Models.ProductoModel;
import com.gazelle.gazelle.Repositories.ProductoRepository;

@Service
public class ProductoService {
    
    @Autowired
    ProductoRepository productoRepository;

    //Método para guardar producto
    public void guardarProducto(ProductoModel producto) {
        this.productoRepository.save(producto);
    }

    //Método para listar todos los productos
    public List<ProductoModel> traerTodos() {
        return this.productoRepository.findAll();
    }

    //Método para buscar por ID
    public Optional<ProductoModel> buscarPorId(String id) {
        return this.productoRepository.findById(id);
    }

    //Método para buscar por nomrbreProducto
    public ProductoModel buscarProducto(String nombreProducto) {
        return this.productoRepository.findByNombreProducto(nombreProducto.toLowerCase()).orElse(new ProductoModel());
    }

}