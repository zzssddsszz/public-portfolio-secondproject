//package com.javatechie.cloud.gateway.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.oauth2.client.oidc.web.server.logout.OidcClientInitiatedServerLogoutSuccessHandler;
//import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//import org.springframework.security.web.server.header.XFrameOptionsServerHttpHeadersWriter.Mode;
//
//@Configuration
//public class SecurityConfig {
//// https://blog.jdriven.com/2019/11/spring-cloud-gateway-with-openid-connect-and-token-relay/
//    @Bean
//    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http,
//                                                     ReactiveClientRegistrationRepository clientRegistrationRepository) {
//        // Authenticate through configured OpenID Provider
//        http.oauth2Login();
//        // Also logout at the OpenID Connect provider
//        http.logout(logout -> logout.logoutSuccessHandler(new OidcClientInitiatedServerLogoutSuccessHandler(
//                clientRegistrationRepository)));
//        // Require authentication for all requests
//        http.authorizeExchange().anyExchange().authenticated();
//        // Allow showing /home within a frame
//        http.headers().frameOptions().mode(Mode.SAMEORIGIN);
//        // Disable CSRF in the gateway to prevent conflicts with proxied service CSRF
//        http.csrf().disable();
//        return http.build();
//    }
//
//}
//
////    @Bean
////    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
////        http.authorizeExchange(exchanges ->
////                exchanges
////                        .pathMatchers("/payment/main").authenticated()
////
////        ).oauth2ResourceServer(oauth2 -> oauth2
////                .jwt(Customizer.withDefaults())
////                .and()
////                .exceptionHandling().accessDeniedHandler(new HttpStatusServerAccessDeniedHandler(HttpStatus.OK))
////        );
////
////        return http.build();
////    }
//
//
////    @Bean
////    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
////        http
////                .authorizeExchange()
////                .pathMatchers("/index")
////                .permitAll()
////                .and()
////                .authorizeExchange()
////                .anyExchange()
////                .authenticated()
////                .and()
////                .oauth2Login(); // to redirect to oauth2 login page.
////
////        return http.build();
////    }
//    // http://localhost:8989/index
//    // http://localhost:8989/user/user
////    @Bean
////    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
////        http.authorizeExchange(exchanges -> exchanges.anyExchange().authenticated())
////                .oauth2Login(withDefaults());
////        http.csrf().disable();
////        return http.build();
////    }
//
//
//
////http://localhost:8080/login/oauth2/code/keycloak
