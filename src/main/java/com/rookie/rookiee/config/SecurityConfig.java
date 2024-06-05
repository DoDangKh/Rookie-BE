package com.rookie.rookiee.config;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor

public class SecurityConfig {

        private final UserAuthenticationEntryPoint userAuthenticationEntryPoint;

        private final UserAuthProvider userAuthProvider;

        @Bean
        SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

                http.cors(cors -> cors.configurationSource((request) -> {
                        CorsConfiguration configuration = new CorsConfiguration();
                        configuration.setAllowedOrigins(List.of("*"));
                        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
                        configuration.setAllowedHeaders(List.of("Authorization", "Content-type", "header"));
                        return configuration;
                }))
                                .exceptionHandling(handling -> handling
                                                .authenticationEntryPoint(userAuthenticationEntryPoint))
                                .addFilterBefore(new JwtAuthFilter(userAuthProvider),
                                                UsernamePasswordAuthenticationFilter.class)
                                .csrf(csrf -> csrf.disable())
                                .sessionManagement(management -> management
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .authorizeHttpRequests((requests) -> requests
                                                .requestMatchers(HttpMethod.GET,
                                                                "/swagger-resources",
                                                                "/swagger-resources/**",
                                                                "/configuration/ui",
                                                                "/configuration/security",
                                                                "/swagger-ui.html",
                                                                "/webjars/**",
                                                                "/v3/api-docs/**",
                                                                "/api/public/**",
                                                                "/api/public/authenticate",
                                                                "/actuator/*",
                                                                "/swagger-ui/**")
                                                .permitAll()
                                                .requestMatchers(HttpMethod.POST, "/api/v1/auth/**").permitAll()
                                                .requestMatchers("/api/v1/admin/**")
                                                .hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                                                .requestMatchers("/images/**").permitAll()
                                                .requestMatchers("/api/v1/product/filter").permitAll()

                                                .requestMatchers("/api/v1/category/all").permitAll()
                                                .requestMatchers("/api/v1/product/all").permitAll()
                                                .requestMatchers(HttpMethod.GET, "/api/v1/product/**").permitAll()
                                                .anyRequest().permitAll());
                return http.build();
        }

        @Bean
        public OpenAPI customizeOpenAPI() {
                final String securitySchemeName = "bearerAuth";
                return new OpenAPI()
                                .addSecurityItem(new SecurityRequirement()
                                                .addList(securitySchemeName))
                                .components(new Components()
                                                .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                                                .name(securitySchemeName)
                                                                .type(SecurityScheme.Type.HTTP)
                                                                .scheme("bearer")
                                                                .bearerFormat("JWT")));
        }
}
