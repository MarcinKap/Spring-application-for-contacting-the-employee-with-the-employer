package com.example.Project_Spring.controllers.restControllers;


import com.example.Project_Spring.models.UserRest;
import com.example.Project_Spring.security.CustomUserService;
//import com.example.Project_Spring.security.JwtGenerator;
import com.example.Project_Spring.security.JwtGenerator;
import com.example.Project_Spring.security.UserApp;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.sql.Date;

@RestController
@AllArgsConstructor
@RequestMapping(produces = {"application/json"})
public class LoginRestController {
    CustomUserService customUserService;

    @RequestMapping(method = RequestMethod.GET , value = {"/restLogIn"})
//    @GetMapping("/restLogIn")
   public UserRest restLogin (Model model, @RequestParam(value = "email") String email, @RequestParam(value = "password") String password){
//        SecretKey keys = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        SecretKey keys = JwtGenerator.getInstance().getKey();

        System.out.println("Logowanie rest");
        Long now = System.currentTimeMillis();

        if (customUserService.logIn(email, password)){
            UserApp userApp = customUserService.findUserByEmail(email);
            String token = Jwts.builder()
                    .setSubject(email) // 1
                    .claim("roles", "user") // 2
                    .setIssuedAt(new Date(now)) // 3
                    .setExpiration(new Date(now + 1000000)) // 4
                    .signWith(keys, SignatureAlgorithm.HS512)// 5)
                    .compact();

            UserRest userRest = UserRest.builder()
                    .email(userApp.getEmail())
                    .name(userApp.getName())
                    .lastName(userApp.getLastName())
                    .role(userApp.getMainRole())
                    .token(token)
                    .id(userApp.getId())
                    .build();



            return userRest;

        }
        return null;

    }






}
