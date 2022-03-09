package com.javatechie.ps.api.config;

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


//@KeycloakConfiguration
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

//    http://localhost:9191/home
//    http://localhost:9191/payment/security
//    즉 /app* 외에는 모두 KeyCloak이 작동을 해야 한다.
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












//        http
//                .authorizeRequests
//                    (authorize -> authorize
//                        .antMatchers("/payment/main").permitAll()
//                        .anyRequest()
//                        .authenticated()
//                    ).oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);     // you will need a token to access the page
////                .exceptionHandling().accessDeniedPage("/api/deny");

//        http.cors()

          // token을 사용하는 방식이기 때문에 csrf를 disable합니다.
//          http.csrf().disable()
//
//                .authorizeRequests()
//                .antMatchers("/payment/main")
//                .permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
//                ;


//    @Override
//    protected void configure(HttpSecurity http) throws Exception
//    {
//        super.configure(http);
//        http
//                .authorizeRequests()
//                .antMatchers("/payment/main").permitAll()
//                .and()
//                .authorizeRequests()
//                .anyRequest().permitAll()
//                .and()
//                .logout()
//                .logoutSuccessUrl("/user/test2");
//    }


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
//
//        return http.build();
//    }



