package com.gazelle.gazelle.Controllers;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gazelle.gazelle.Models.CompraModel;
import com.gazelle.gazelle.Services.CompraService;



@RestController
@RequestMapping("/api")
public class CompraController {

        @Autowired
        CompraService compraService;

        @PostMapping("/compras")
        public ResponseEntity<Map<String, String>> guardar(@Valid @RequestBody CompraModel compra, Errors error){
            this.compraService.guardarCompra(compra);
            Map<String, String> respuesta = new HashMap<>();
            respuesta.put("mensaje", "Se agregó la compra correctamente");
            respuesta.put("estado", "true"); 
            return ResponseEntity.ok(respuesta);

        }

        @GetMapping("/compras")
        public List<CompraModel> mostrar(){
            return compraService.traerTodos();
        }
        
       
    
}