package com.example.Project_Spring.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtFilter implements javax.servlet.Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("wchodzi w filtr");

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        String header = httpServletRequest.getHeader("authorization");

        System.out.println(header);

        if (!header.startsWith("Bearer "))
            System.out.println("blad z bearer");

        if (httpServletRequest == null){
            System.out.println("blad z httpservletrequest");
        }


        if (httpServletRequest == null || !header.startsWith("Bearer ")){
            throw new ServletException("Wrong or empty headset");
        }else {
            System.out.println("wchodzi w else");
            try {

                String token = header.substring(7);
                System.out.println("po tokenie");
                System.out.println(token);
                Claims claims = Jwts.parserBuilder().setSigningKey(JwtGenerator.getInstance().getKey()).build().parseClaimsJws(token).getBody();
                servletRequest.setAttribute("claims", claims);
            }
            catch (Exception e){
                System.out.println("catch");
                throw new ServletException("Wrong key");
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);


    }
}
