package com.example.Project_Spring.security;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.security.Key;

public class JwtGenerator {

    private static JwtGenerator INSTANCE;
    private static SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public JwtGenerator() {
    }

    public static JwtGenerator getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new JwtGenerator();
        }
        return INSTANCE;
    }



    public SecretKey getKey(){
        return secretKey;
    }



}
