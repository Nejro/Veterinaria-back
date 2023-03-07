/*package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig   {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception{
        return  http
                .httpBasic()
                .and().authorizeHttpRequests()
                .anyRequest().authenticated()
                .and().build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
       return  new InMemoryUserDetailsManager(
               User.withUsername("user")
                       .password(passwordEncoder().encode("password123"))
                       .roles("USER")
                       .authorities("read")
                       .build(),
               User.withUsername("admin")
                       .password(passwordEncoder().encode("password123"))
                       .roles("ADMIN")
                       .authorities("read","write")
                       .build()
       );

    }
       @Bean
        public PasswordEncoder passwordEncoder(){
           return PasswordEncoderFactories.createDelegatingPasswordEncoder();
       }
}
*/