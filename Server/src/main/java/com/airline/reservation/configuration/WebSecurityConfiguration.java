
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.airline.reservation.configuration;

import com.airline.reservation.security.AccessTokenProcessingFilter;
import com.airline.reservation.security.AccessTokenUserDetailsService;
import com.airline.reservation.security.config.SecurityConfig;
import com.airline.reservation.security.util.TokenGenerator;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;

import static org.springframework.http.HttpMethod.OPTIONS;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;

 
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    public WebSecurityConfiguration() {
        super(true);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       
        http.cors()
        .and()
                .requestMatcher(new NegatedRequestMatcher(new AntPathRequestMatcher("/error")))
                .addFilter(accessTokenProcessingFilter())
                .authenticationProvider(preAuthenticatedAuthenticationProvider())
                .exceptionHandling().and()
                .headers().and()
                .sessionManagement().sessionCreationPolicy(STATELESS).and()
                .securityContext().and()
                .anonymous().and()
                .authorizeRequests()
//                .antMatchers("/**").permitAll()
                .antMatchers(GET,"/users/GetUsers").access("hasRole('ROLE_ADMIN')")
                .antMatchers(PUT,"/users/changeStatus/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers(GET,"/users/GetCompany").access("hasRole('ROLE_ADMIN')")
                .antMatchers(PUT,"/users").access("hasRole('ROLE_PASSENGER')")
                .antMatchers(PUT,"/users/changePwd").access("hasRole('ROLE_PASSENGER')or hasRole('ROLE_ADMIN')or hasRole('ROLE_COMPANY')")
                .antMatchers(GET,"/users/**").access("hasRole('ROLE_PASSENGER')or hasRole('ROLE_ADMIN')or hasRole('ROLE_COMPANY')")
                .antMatchers("/users/signup").anonymous()
                .antMatchers(POST,"/airplane").access("hasRole('ROLE_COMPANY')")
                .antMatchers(GET,"/airplane").access("hasRole('ROLE_ADMIN')")
                .antMatchers(GET,"/airplane/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_COMPANY')")
                .antMatchers(PUT,"/airplane/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_COMPANY')")
                .antMatchers(DELETE,"/airplane/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_COMPANY')")
                .antMatchers(POST,"/bookings/**").access("hasRole('ROLE_PASSENGER')")
                .antMatchers(POST,"/bookings/addBooking").access("hasRole('ROLE_PASSENGER')")
                .antMatchers(DELETE,"/bookings").access("hasRole('ROLE_PASSENGER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_COMPANY')")
                .antMatchers(GET,"/bookings/status/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers(GET,"/bookings/download/**").access("hasRole('ROLE_ADMIN')or hasRole('ROLE_COMPANY')")
                .antMatchers(DELETE,"/bookings").access("hasRole('ROLE_ADMIN')or hasRole('ROLE_COMPANY')")
                .antMatchers(PUT,"/bookings/changeStatus/**/**").access("hasRole('ROLE_ADMIN')or hasRole('ROLE_COMPANY')")
                .antMatchers(PUT,"/bookings/cancelBooking/**").access("hasRole('ROLE_PASSENGER')")
                .antMatchers(GET,"/bookings/cancelBooking/**").access("hasRole('ROLE_PASSENGER')")
                .antMatchers(GET,"/bookings/getById/**").access("hasRole('ROLE_PASSENGER')")
                .antMatchers(GET,"/bookings/getByCompany/**").access("hasRole('ROLE_COMPANY')")
                .antMatchers(POST,"/company/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers(GET,"/company/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers(PUT,"/company/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers(POST,"/flight").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_COMPANY')")
                .antMatchers(GET,"/flight/findAll").access("hasRole('ROLE_ADMIN')")
                .antMatchers(GET,"/flight/findAll/**").access("hasRole('ROLE_ADMIN')or hasRole('ROLE_COMPANY')")
                .antMatchers(GET,"/flight/**").access("hasRole('ROLE_ADMIN')or hasRole('ROLE_COMPANY') or hasRole('ROLE_PASSENGER')")
                .antMatchers(GET,"/flight/getRandom").permitAll()
                .antMatchers(GET,"/flight/getTwoRandom").permitAll()
                .antMatchers(PUT,"/flight/**").access("hasRole('ROLE_ADMIN')or hasRole('ROLE_COMPANY')")
                .antMatchers(DELETE,"/flight/**").access("hasRole('ROLE_ADMIN')or hasRole('ROLE_COMPANY')")

              

//                .antMatchers("/company/**").permitAll()
                .antMatchers("/seat/**").permitAll()
                .antMatchers("/flight/**").permitAll()
                // .antMatchers(OPTIONS, "/login").anonymous()
//                .antMatchers("/bookings/**").permitAll()
                .antMatchers( "/login").permitAll()
//                .antMatchers(POST, "/airplane").permitAll()
//                .antMatchers(POST, "/airplane").permitAll()
                .antMatchers(OPTIONS, "/**").anonymous() 
       
                .anyRequest().authenticated();
    }
    @Bean
    protected AccessTokenUserDetailsService accessTokenUserDetailsService() {
        return new AccessTokenUserDetailsService();
    }
    @Bean
    protected PreAuthenticatedAuthenticationProvider preAuthenticatedAuthenticationProvider() {
        PreAuthenticatedAuthenticationProvider authProvider = new PreAuthenticatedAuthenticationProvider();
        authProvider.setPreAuthenticatedUserDetailsService(accessTokenUserDetailsService());
        return authProvider;
    }
    @Bean
    protected AccessTokenProcessingFilter accessTokenProcessingFilter() throws Exception {
        AccessTokenProcessingFilter filter = new AccessTokenProcessingFilter();
        filter.setAuthenticationManager(authenticationManager());
        return filter;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    @Bean
    @ConfigurationProperties("app.security")
    public SecurityConfig securityConfig() {
        return new SecurityConfig();
    }
    @Bean
    @ConfigurationProperties("app.security.configuration")
    public TokenGenerator tokenGenerator(SecurityConfig securityConfig) {
        return new TokenGenerator(securityConfig.getTokenGeneratorPassword(), securityConfig.getTokenGeneratorSalt());
    }
}

