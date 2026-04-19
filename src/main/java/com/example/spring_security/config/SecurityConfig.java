package com.example.spring_security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.http.UserDetailsServiceFactoryBean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception{
        security.csrf(customizer -> customizer.disable()).authorizeHttpRequests(requests -> requests.anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults()).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return security.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails userDetails = User.withDefaultPasswordEncoder().username("jayneel").password("j@yneel").roles("USER").build();

        UserDetails adminDetails = User.withDefaultPasswordEncoder().username("admin").password("admin@123").roles("ADMIN").build();

        return new InMemoryUserDetailsManager(userDetails, adminDetails);
    }
}
