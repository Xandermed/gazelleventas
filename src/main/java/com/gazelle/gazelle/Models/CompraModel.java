package com.gazelle.gazelle.Models;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "compras")
public class CompraModel {
    
    @Id
    private String id;
    private String fecha;
    private ClienteModel cliente;
    private ArrayList<ProductoModel> producto;
    //private ProductoModel producto;
    
    
    public String getId() {
        return id;
    }
   
    

    public ArrayList<ProductoModel> getProducto() {
        return producto;
    }



    public void setProducto(ArrayList<ProductoModel> producto) {
        this.producto = producto;
    }



    public void setId(String id) {
        this.id = id;
    }
   
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public ClienteModel getCliente() {
        return cliente;
    }
    public void setCliente(ClienteModel cliente) {
        this.cliente = cliente;
    }
    
}