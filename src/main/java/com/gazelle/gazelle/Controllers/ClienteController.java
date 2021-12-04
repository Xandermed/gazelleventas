package com.gazelle.gazelle.Controllers;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import com.gazelle.gazelle.Models.ClienteModel;
import com.gazelle.gazelle.Services.ClienteService;
import com.gazelle.gazelle.exceptions.CustomeException;
import com.gazelle.gazelle.utils.Autorizacion;
import com.gazelle.gazelle.utils.BCrypt;

@CrossOrigin("*")
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
                respuesta.put("estado", "true");
    
            } else {
                respuesta.put("mensaje", "El usuario ya esta registrado");
    
            }
    
            return ResponseEntity.ok(respuesta);

        }

        @PostMapping("/clientes/login")
        //crear metodo para el login de usuario
        public ResponseEntity<Map<String,String>> acceder (@RequestBody ClienteModel cliente){
    
            //crear un usuario auxiliar deltipo usuarioModel
            ClienteModel auxiliar = this.clienteService.buscarNick(cliente.getNick());
    
            //crear map para el mesnajer
            Map<String,String> respuesta = new HashMap<>();
            //condiciones de acceso
            //que el usarname no este vacio
    
            if (auxiliar.getNick()== null){
                respuesta.put("mensaje", "Usuario o contraseña incorrectos");
            }else{
                if(!BCrypt.checkpw(cliente.getPassword(), auxiliar.getPassword())){
                    respuesta.put("mensaje", "Usuario o contraseña incorrectos");
                }else{
                    respuesta.put("mensaje", "Se accedio correctamente");
                    String hash="";
                    Long tiempo = System.currentTimeMillis();
    
                    if (auxiliar.getId() != ""){
                        hash = Jwts.builder()
                                        .signWith(SignatureAlgorithm.HS256, Autorizacion.KEY)
                                        .setSubject(auxiliar.getNick())
                                        .setIssuedAt(new Date(tiempo))
                                        .setExpiration(new Date(tiempo + 900000 ))
                                        .claim("nick",auxiliar.getNick())
                                        .claim("correo",auxiliar.getCorreo())
                                        .compact();
                    }
    
                    auxiliar.setHash(hash);
                    respuesta.put("hash",hash);
                }
            }
            return ResponseEntity.ok(respuesta);
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