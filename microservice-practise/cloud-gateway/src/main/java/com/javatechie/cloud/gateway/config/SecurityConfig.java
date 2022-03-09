package com.javatechie.cloud.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebFluxSecurity
//@KeycloakConfiguration
//@EnableGlobalMethodSecurity(jsr250Enabled = true)
//@Import(KeycloakSpringBootConfigResolver.class)
public class SecurityConfig{

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        KeycloakAuthenticationProvider authenticationProvider = new KeycloakAuthenticationProvider();
//        authenticationProvider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
//        auth.authenticationProvider(authenticationProvider);
//    }
//    /**
//     * Defines the session authentication strategy.
//     */
//    @Bean
//    @Override
//    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
//        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
//    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {

        http
                .authorizeExchange()
                .pathMatchers("/index", "/home")
                .permitAll()
                .and()

                .authorizeExchange()
                .anyExchange()
                .authenticated()
                .and()
                .oauth2Login()
                .and()
                .oauth2ResourceServer()
                .jwt(); // to redirect to oauth2 login page.

        return http.build();


//        http
//                .csrf().disable()
//                .authorizeExchange()
//                .pathMatchers("/index", "/home")
//                .permitAll()
//            .and()
//                .authorizeExchange(exchanges -> exchanges
//                        .anyExchange().authenticated())
//                .oauth2Login(withDefaults());
//        http.csrf().disable();
        // to redirect to oauth2 login page.

//        return http.build();


    }
}
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/index", "/home")
//                .permitAll()
//                .and()
//                .authorizeRequests()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .oauth2Login()
//                .and()
//                .oauth2Client();
        // to redirect to oauth2 login page.

//}
