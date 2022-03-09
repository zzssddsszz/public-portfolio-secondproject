package com.javatechie.ps.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.javatechie.ps.api.entity.Payment;
import com.javatechie.ps.api.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Random;

@Slf4j
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/doPayment")
    public Payment doPayment(@RequestBody Payment payment) throws JsonProcessingException {
        return paymentService.doPayment(payment);
    }

    @GetMapping("/{orderId}")
    public Payment findPaymentHistoryByOrderId(@PathVariable int orderId) throws JsonProcessingException {
        return paymentService.findPaymentHistoryByOrderId(orderId);
    }

    @GetMapping("/security")
    public String securityTest(){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        return "Scopes: " + authentication.getAuthorities() +"/n";
    }

    @GetMapping("/main")        // this page has permit all
    public String mainPage(){
        return "payment";
    }

    @GetMapping("/admin")        // this page has permit all
    @RolesAllowed("admin")
    public String adminPage(){
        return "admin page";
    }

    @GetMapping("/token")
    public String getToken(HttpServletRequest request){
        KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) request.getUserPrincipal();
        KeycloakPrincipal p = (KeycloakPrincipal) token.getPrincipal();
        KeycloakSecurityContext session = p.getKeycloakSecurityContext();
        AccessToken accessToken = session.getToken();
        String username = accessToken.getPreferredUsername();
        String realmName = accessToken.getIssuer();

        System.out.println("---------------------------- " + username + " -----------------------------------");
        System.out.println("---------------------------- " + realmName + " -----------------------------------");
        return "this is token page : " + username + " realmName : " + realmName ;
    }

}
