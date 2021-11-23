package com.gazelle.gazelle.Controllers;


import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gazelle.gazelle.Models.ClienteModel;
import com.gazelle.gazelle.Services.ClienteService;
import com.gazelle.gazelle.utils.BCrypt;


@RestController
@RequestMapping("/api")
public class ClienteController {

        @Autowired
        ClienteService clienteService;

        @PostMapping("/clientes")
        public ResponseEntity<Map<String, String>> guardar(@Valid @RequestBody ClienteModel cliente, Errors error){

            Map<String, String> respuesta = new HashMap<>();

            // Ciframos la contraseña con la clase BCrypt
            cliente.setPassword(BCrypt.hashpw(cliente.getPassword(), BCrypt.gensalt()));
    
            ClienteModel u = this.clienteService.buscarNick(cliente.getNick());
            if (u.getId() == null) {
                this.clienteService.guardarUsuario(cliente);
                respuesta.put("mensaje", "Se agregó correctamente el usuario");
    
            } else {
                respuesta.put("mensaje", "El usuario ya esta registrado");
    
            }
    
            return ResponseEntity.ok(respuesta);

        }
    
}