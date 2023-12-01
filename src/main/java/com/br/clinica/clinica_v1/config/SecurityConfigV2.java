package com.br.clinica.clinica_v1.config;

import com.br.clinica.clinica_v1.authentication.filter.FilterToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.Filter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfigV2 {

    @Autowired
    private FilterToken filter;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager() ;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/pacientes/**").hasRole("USER")
                .antMatchers("/cpf-pacientes/**").hasRole("ADMIN")
                .antMatchers("/auth/login").permitAll()
                .antMatchers("/v2/api-docs", "/swagger-resources/**", "/webjars/**", "/swagger-ui/**").permitAll()
                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .defaultSuccessUrl("/swagger-ui/index.html").permitAll()
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .and().addFilterBefore((Filter) filter, (Class<? extends Filter>) UsernamePasswordAuthenticationFilter.class);;
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
