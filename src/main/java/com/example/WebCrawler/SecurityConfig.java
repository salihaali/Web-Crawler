package com.example.WebCrawler;

import com.example.WebCrawler.Services.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;



@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // http
        // .authorizeRequests(requests -> requests
        //                 .requestMatchers("/", "/").permitAll()
        //                 .requestMatchers("/register").anonymous()
        //                 .requestMatchers("/admin/teach").hasAuthority("ROLE_ADMIN".toString())
        //                 .anyRequest().authenticated()
        //         );

        // http.formLogin(form -> form
        //         .loginPage("/login")
        //         .successHandler(savedRequestAwareAuthenticationSuccessHandler())
        //         .loginProcessingUrl("/login")
        //         .defaultSuccessUrl("/chat", true)
        //         .failureUrl("/login?error=true")
        //         .permitAll()
        // );
        // http.logout(LogoutConfigurer::permitAll);

        http
            .authorizeRequests(requests -> requests
                // .requestMatchers("/admin") // Require authentication for /admin routes
                // .authenticated()
                .anyRequest().permitAll() // Allow all other requests to any user
            );

        return http.build();
    }

    private AuthenticationSuccessHandler savedRequestAwareAuthenticationSuccessHandler() {
        SavedRequestAwareAuthenticationSuccessHandler handler = new SavedRequestAwareAuthenticationSuccessHandler();
        handler.setTargetUrlParameter("redirectTo");
        return handler;
    }
}