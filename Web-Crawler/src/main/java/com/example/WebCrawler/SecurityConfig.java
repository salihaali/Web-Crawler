package com.example.WebCrawler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(request -> request.requestMatchers(new AntPathRequestMatcher("/**"))
                .permitAll())
            // .authorizeHttpRequests(request -> request.requestMatchers(new AntPathRequestMatcher("/customers/**"))
            //     .hasRole("ADMIN")
            //     .anyRequest()
            //     .authenticated())
            .httpBasic(Customizer.withDefaults())
            .build();
    }
}