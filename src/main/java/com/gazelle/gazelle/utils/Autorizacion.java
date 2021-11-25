package com.gazelle.gazelle.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;

    @Component
public class Autorizacion implements Filter {
    // Llave de cifrado y descifrado
    public static final String KEY = "HhgcbM41xIRhzxaH1U6kOYBA34znJa94Qb5Ceogy";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        String url = req.getRequestURI();
        // http://localhost:8080 ------->url

        if (url.contains("/api/clientes") || url.contains("/api/clientes/login")) {
            chain.doFilter(request, response);
        }else{
            String hash=req.getHeader("Authorization");
            if(hash==null || hash.trim().equals("")){
                response.setContentType("application/json");
                String body="{\"autorizacion\":\"NO\"}";
                response.getWriter().write(body);
            }
            
            try{
                //Lectura de carga util del JWT
                Jws<Claims> claims = Jwts.parser().setSigningKey(KEY).parseClaimsJws(hash);
                if((url.contains("/api/compras") || url.contains("/api/productos"))&& (!claims.getBody().get("nick").equals(""))){
                    chain.doFilter(request, response);
                }

            }catch(MalformedJwtException e){
                response.setContentType("application/json");
                String body="{\"autorizacion\":\"TOKEN NO VALIDO\"}";
                response.getWriter().write(body);
            }
            



                
        }
    }

}