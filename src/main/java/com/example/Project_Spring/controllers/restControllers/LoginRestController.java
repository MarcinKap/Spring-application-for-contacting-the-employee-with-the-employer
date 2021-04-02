package com.example.Project_Spring.controllers.restControllers;


import com.auth0.jwt.interfaces.Claim;
import com.example.Project_Spring.security.CustomUserService;
//import com.example.Project_Spring.security.JwtGenerator;
import com.example.Project_Spring.security.JwtGenerator;
import com.example.Project_Spring.security.UserApp;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.sql.Date;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping(produces = {"application/json"})
public class LoginRestController {
    CustomUserService customUserService;


    @PostMapping("/restLogIn")
   public String restLogin (@RequestParam(value = "email") String email, @RequestParam(value = "password") String password){
//        SecretKey keys = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        SecretKey keys = JwtGenerator.getInstance().getKey();

        System.out.println("Logowanie rest");
        Long now = System.currentTimeMillis();

        if (customUserService.logIn(email, password)){
            return Jwts.builder()
                    .setSubject(email) // 1
                    .claim("roles", "user") // 2
                    .setIssuedAt(new Date(now)) // 3
                    .setExpiration(new Date(now + 100000)) // 4
                    .signWith(keys, SignatureAlgorithm.HS512)
                    .compact(); // 5
        }
        return null;

    }






}
