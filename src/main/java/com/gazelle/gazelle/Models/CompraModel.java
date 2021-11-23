package com.gazelle.gazelle.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "compras")
public class CompraModel {
    
    @Id
    private String id;
    private String fecha;
    private ClienteModel cliente;
    private ProductoModel producto;
    private int cantidad;
    
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
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
    public ProductoModel getProducto() {
        return producto;
    }
    public void setProducto(ProductoModel producto) {
        this.producto = producto;
    }

    
}