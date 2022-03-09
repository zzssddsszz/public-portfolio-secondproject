package com.javatechie.cloud.gateway;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

//    https://dzone.com/articles/how-to-use-spring-cloud-gateway-with-oauth-20-patt

    @GetMapping("/index")
    public String indexPage(){
        return "index page";
    }

    @GetMapping("/security")
    public String securityTest(){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        return "Scopes: " + authentication.getAuthorities() +"/n";
    }
}
