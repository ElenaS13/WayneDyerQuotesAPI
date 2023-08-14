package com.e.sb;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@Configuration
public class SecurityConfig extends AbstractHttpConfigurer<SecurityConfig, HttpSecurity> {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/public/**").permitAll() // Allow access to public paths
                                .anyRequest().authenticated() // Require authentication for other paths
                );
    }
}
