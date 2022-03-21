package com.jelmerpijnappel.jelmerqa.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * The web security configuration.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/register-question").permitAll()
                    .antMatchers("/get-questions").permitAll()
                    .antMatchers("/answer-question").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .csrf().disable()
                .headers()
                    .xssProtection()
                    .and()
                .contentSecurityPolicy("script-src 'self'");
    }
}
