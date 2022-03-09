//package com.javatechie.cloud.gateway.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
//
////@Configuration
//
////@Configuration
//@EnableWebFluxSecurity
//public class SecurityConfig{
//
////    @Bean   // WebFluxSecurity 이기 때문에 SecurityWebFilterChain 을 bean 으로 등록한다
////    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
////        http.authorizeExchange(exchanges -> exchanges.anyExchange().authenticated())
////                .oauth2Login(withDefaults());
////        http.csrf().disable();
////        return http.build();
////    }
//
//
////    @Bean
////    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) {
////        serverHttpSecurity.authorizeExchange(exchange -> exchange.anyExchange().authenticated())
////                .oauth2ResourceServer(ServerHttpSecurity.OAuth2ResourceServerSpec::jwt);
////
////        serverHttpSecurity.csrf().disable();
////        return serverHttpSecurity.build();
////    }
//}
//
//
////    @Bean
////    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) {
////        serverHttpSecurity.authorizeExchange(exchange -> exchange.anyExchange().authenticated())
////                .oauth2ResourceServer(ServerHttpSecurity.OAuth2ResourceServerSpec::jwt);
////
////        serverHttpSecurity.csrf().disable();
////        return serverHttpSecurity.build();
////    }
////
//
//
////
////
