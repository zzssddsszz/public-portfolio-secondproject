package com.javatechie.demo.controller;


import com.javatechie.demo.entity.User;
import com.javatechie.demo.mapper.UserMapper;
import com.javatechie.demo.repository.UserRepository;
//import org.keycloak.KeycloakPrincipal;
//import org.keycloak.KeycloakSecurityContext;
//import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
//import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;
//    private Map<String, User> userMap;
    private UserMapper userMapper;

//
    public UserController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

//    @PostMapping("/register")
//    public User userRegister(@RequestBody User user) throws JsonProcessingException {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        return userRepository.save(user);
//    }


    // this method can be accessed by user whose role is user
    @GetMapping("/{userid}")
    @RolesAllowed("user")
    public ResponseEntity<Optional<User>> getUser(@PathVariable Long userid){
        return ResponseEntity.ok(userRepository.findById(userid));
    }

    // this method can be accessed by user whose role is admin
    @GetMapping(value = "/lists", produces = "application/json")
    @RolesAllowed("admin")
    public List<User> userLists(){
        return userMapper.getUserInfo();
//        return userRepository.findAll();
    }

    @GetMapping("/user")
    @RolesAllowed("user")
    public String testing1(){
        return "this is for user page";
    }

    @GetMapping("/security")
    @RolesAllowed("user")
    public String securityTest(){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        return "Scopes: " + authentication.getAuthorities() +"/n";
    }

    @GetMapping("/test2")
    public String testing2(){
        return "this is only for testing user";
    }

//    @GetMapping("/token")
//    public String getToken(HttpServletRequest request){
//        KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) request.getUserPrincipal();
//        KeycloakPrincipal p = (KeycloakPrincipal) token.getPrincipal();
//        KeycloakSecurityContext session = p.getKeycloakSecurityContext();
//        AccessToken accessToken = session.getToken();
//        String username = accessToken.getPreferredUsername();
//        String realmName = accessToken.getIssuer();
//
//        System.out.println("---------------------------- " + username + " -----------------------------------");
//        System.out.println("---------------------------- " + realmName + " -----------------------------------");
//        return "this is token page : " + username + " realmName : " + realmName ;
//    }

    @GetMapping("/id")
    public String getProduct(Principal principal) {
    // e4acd668-0491-4a31-b93f-a783377d8dfd
    // e4acd668-0491-4a31-b93f-a783377d8dfd
        return "Response from KeyCloak Service, User Id: " + principal.getName();
    }


//    @GetMapping("/teacher")
//    public HashMap teacher(@RequestHeader("Authorization") String authHeader) {
//        try {
//            DecodedJWT jwt = JWT.decode(authHeader.replace("Bearer", "").trim());
//
//            // check JWT is valid
//            Jwk jwk = jwtService.getJwk();
//            Algorithm algorithm = Algorithm.RSA256((RSAPublicKey) jwk.getPublicKey(), null);
//
//            algorithm.verify(jwt);
//
//            // check JWT role is correct
//            List<String> roles = ((List)jwt.getClaim("realm_access").asMap().get("roles"));
//            if(!roles.contains("teacher"))
//                throw new Exception("not a teacher role");
//
//            // check JWT is still active
//            Date expiryDate = jwt.getExpiresAt();
//            if(expiryDate.before(new Date()))
//                throw new Exception("token is expired");
//
//            // all validation passed
//            return new HashMap() {{
//                put("role", "teacher");
//            }};
//        } catch (Exception e) {
//            logger.error("exception : {} ", e.getMessage());
//            return new HashMap() {{
//                put("status", "forbidden");
//            }};
//        }
//    }

    @GetMapping("/admin")
    @RolesAllowed("admin")
    public String adminPage(){
        return "this is for admin page";
    }

    @GetMapping(path = "/logout")
    @RolesAllowed({ "user", "admin", "" })
    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "/";
    }
}