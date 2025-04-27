package com.example.demo.config;


import com.example.demo.config.filter.CustomSecurityFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class WebFilterConfig {

private final CustomSecurityFilter customSecurityFilter;
    // User Creation
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        // InMemoryUserDetailsManager setup with two users
        UserDetails admin = User.withUsername("ilkin01")
                .password(encoder.encode("123"))
                .roles("ADMIN")
                .build();

        UserDetails user = User.withUsername("ilkin")
                .password(encoder.encode("123"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }

    // Configuring HttpSecurity
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(customSecurityFilter,UsernamePasswordAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF for simplicity
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/v1/card/**").permitAll() // Permit all access
                        .requestMatchers("/v1/permission/**").permitAll() // Permit all access
                        .requestMatchers("/auth/user/**").authenticated() // Require authentication for /auth/user/**
                        .requestMatchers("/auth/admin/**").authenticated() // Require authentication for /auth/admin/**
                ).sessionManagement(
                        c -> c.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        ))
                .formLogin(Customizer.withDefaults());// Disable form-based login
               // .httpBasic(AbstractHttpConfigurer::disable);  // Disable basic HTTP login (if you want to remove all login forms)

        return http.build();
    }

     //Password Encoding
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
