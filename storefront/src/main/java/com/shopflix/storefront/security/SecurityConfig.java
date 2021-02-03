package com.shopflix.storefront.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import javax.annotation.Resource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource(name = "coreUserDetailsService")
    private UserDetailsService coreUserDetailsService;

    @Resource(name = "coreAuthenticationEntryPoint")
    private AuthenticationEntryPoint coreAuthenticationEntryPoint;

    @Resource(name = "storefrontAuthenticationSuccessHandler")
    private StorefrontAuthenticationSuccessHandler storefrontAuthenticationSuccessHandler;

    @Resource(name = "storefrontAuthenticationFailureHandler")
    private StorefrontAuthenticationFailureHandler storefrontAuthenticationFailureHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests()
                .antMatchers("/api/v1/user/register").permitAll()
                .antMatchers("/merchant/api/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(coreAuthenticationEntryPoint)
                .and()
                .formLogin()
                .loginProcessingUrl("/api/v1/user/login")
                .usernameParameter("uid")
                .passwordParameter("password")
                .successHandler(storefrontAuthenticationSuccessHandler)
                .failureHandler(storefrontAuthenticationFailureHandler)
                .and()
                .logout()
                .logoutUrl("/api/v1/user/logout")
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK))
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()

        .and()
        .csrf().disable();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(coreUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
