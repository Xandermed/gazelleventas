package com.gazelle.gazelle.Controllers;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.gazelle.gazelle.Models.ProductoModel;

import com.gazelle.gazelle.Services.ProductoService;
import com.gazelle.gazelle.exceptions.CustomeException;


@RestController
@CrossOrigin("*")
@RequestMapping("/api")


public class ProductoController {

        @Autowired
        ProductoService productoService;

     

        @PostMapping("/productos")
        public ResponseEntity<Map<String, String>> guardar(@Valid @RequestBody ProductoModel producto, Errors error){

            Map<String, String> respuesta = new HashMap<>();

            ProductoModel u = this.productoService.buscarProducto(producto.getNombreProducto());
            if (u.getId() == null) {
                this.productoService.guardarProducto(producto);
                respuesta.put("mensaje", "Se agregó correctamente el producto");
                respuesta.put("estado", "true");    
            } else {
                respuesta.put("mensaje", "El producto ya esta registrado");
    
            }
    
            return ResponseEntity.ok(respuesta);

        }
        
        @GetMapping("/productos")
        public List<ProductoModel> mostrar(){
            return productoService.traerTodos();
        }
        public void throwError(Errors error){
            String mensaje = "";
            int index = 0; 
            for (ObjectError e: error.getAllErrors()){
                if (index > 0){
                    mensaje += " | ";
                }
                mensaje+=String.format("parametro: %s - Mensaje: %s", e.getObjectName(),e.getDefaultMessage());
            }   
            throw new CustomeException(mensaje);
         }
    
}
