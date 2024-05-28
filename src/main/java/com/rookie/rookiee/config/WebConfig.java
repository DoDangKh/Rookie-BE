package com.rookie.rookiee.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration thisconfig = new CorsConfiguration();
        thisconfig.setAllowCredentials(true);
        thisconfig.addAllowedOrigin("http://localhost:3000");
        thisconfig.setAllowedHeaders((Arrays.asList(
                HttpHeaders.AUTHORIZATION,
                HttpHeaders.CONTENT_TYPE,
                HttpHeaders.ACCEPT)));
        thisconfig.setAllowedMethods(Arrays.asList(
                HttpMethod.GET.name(),
                HttpMethod.POST.name(),
                HttpMethod.PUT.name(),
                HttpMethod.DELETE.name()));
        thisconfig.setMaxAge(3600L);
        thisconfig.setAllowedHeaders(List.of("Authorization", "Content-type"));
        source.registerCorsConfiguration("/**", thisconfig);
        // FilterRegistrationBean bean = new FilterRegistrationBean(new
        // CorsFilter(source));
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        // bean.setOrder(-102);
        return bean;
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").addResourceLocations("file:C:/Users/Khoa/Documents/Rookie/Image/");
    }

    // @Override
    // public void addCorsMappings(CorsRegistry registry) {
    // // TODO Auto-generated method stub
    // registry.addMapping("/**");
    //

}
// sua label category