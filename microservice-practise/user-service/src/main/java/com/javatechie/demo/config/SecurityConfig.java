package com.javatechie.demo.config;

import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;


//@Configuration
//@EnableWebSecurity
//@KeycloakConfiguration
//@ComponentScan(basePackageClasses = KeycloakSecurityComponents.class)
//@EnableGlobalMethodSecurity(jsr250Enabled = true)
//@Import(KeycloakSpringBootConfigResolver.class)

@KeycloakConfiguration
@EnableGlobalMethodSecurity(jsr250Enabled = true)
@Import(KeycloakSpringBootConfigResolver.class)
public class SecurityConfig extends KeycloakWebSecurityConfigurerAdapter
{
    /**
     * Registers the KeycloakAuthenticationProvider with the authentication manager.
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        KeycloakAuthenticationProvider authenticationProvider = new KeycloakAuthenticationProvider();
        authenticationProvider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
        auth.authenticationProvider(authenticationProvider);
    }
    /**
     * Defines the session authentication strategy.
     */
    @Bean
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }
    //    http://localhost:9193/home
    //    http://localhost:9193/user/security
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http
                .authorizeRequests()
                .antMatchers("/home")
                .permitAll()
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutUrl("/home")
                .logoutSuccessUrl("/home");
    }
}

//    @Bean
//    protected SessionRegistry buildSessionRegistry() {
//        return new SessionRegistryImpl();
//    }
//    @Bean
//    @Override
//    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
//        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
//    }
//    @Bean
//    public KeycloakConfigResolver KeycloakConfigResolver() {
//        return new KeycloakSpringBootConfigResolver();
//    }


//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//            .authorizeRequests()
//                .antMatchers("/home")
//                .permitAll()
//                .and()
//            .authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//            .logout()
//                .logoutUrl("/home")
//                .logoutSuccessUrl("/home");
//        .authorizeRequests().anyRequest().permitAll();
//
//    }

//                    .authorizeRequests()
//                .antMatchers("/user/home", "user/home", "/user/test2").permitAll()
//                .and()
//                .authorizeRequests()
////                .antMatchers("/user*").hasRole("USER")
////                .antMatchers("/lists*").hasRole("ADMIN")
//                .anyRequest().permitAll()
//                .and()
//                .logout()
////                .logoutUrl("/user/logout").permitAll()
//                .logoutSuccessUrl("/user/test2");

//        http.csrf().disable().authorizeRequests()
//                .antMatchers("/js/**", "/css/**", "/vendors/**", "/images/**").permitAll()
//                .antMatchers("/**").authenticated()
//                .anyRequest().permitAll()
//                .and().logout().logoutSuccessUrl("/loggedout");


//    @Bean
//    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
//        http.authorizeExchange(exchanges ->
//                exchanges
//                        .pathMatchers("/payment/main").authenticated()
//
//        ).oauth2ResourceServer(oauth2 -> oauth2
//                .jwt(Customizer.withDefaults())
//                .and()
//                .exceptionHandling().accessDeniedHandler(new HttpStatusServerAccessDeniedHandler(HttpStatus.OK))
//        );
//        return http.build();

