package com.shopflix.storefront.security;

import com.shopflix.core.repository.user.CustomerRepository;
import com.shopflix.storefront.config.StorefrontAppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
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
@Import(StorefrontAppConfig.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private StorefrontAppConfig storefrontAppConfig;

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
                .exceptionHandling().authenticationEntryPoint(coreAuthenticationEntryPoint())
                .and()
                .formLogin()
                .loginProcessingUrl("/api/v1/user/login")
                .usernameParameter("uid")
                .passwordParameter("password")
                .successHandler(storefrontAuthenticationSuccessHandler())
                .failureHandler(storefrontAuthenticationFailureHandler())
                .and()
                .logout()
                .logoutUrl("/api/v1/user/logout")
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK))
                .invalidateHttpSession(true)
                .deleteCookies("SESSION")
                .permitAll()
        .and()
        .csrf().disable();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(coreUserDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CoreUserDetailsService coreUserDetailsService() {
        CoreUserDetailsService coreUserDetailsService = new CoreUserDetailsService();
        coreUserDetailsService.setCustomerRepository(customerRepository);
        return coreUserDetailsService;
    }

    @Bean
    public CoreAuthenticationEntryPoint coreAuthenticationEntryPoint() {
        return new CoreAuthenticationEntryPoint();
    }

    @Bean
    public StorefrontAuthenticationFailureHandler storefrontAuthenticationFailureHandler() {
        return new StorefrontAuthenticationFailureHandler();
    }

    @Bean
    public StorefrontAuthenticationSuccessHandler storefrontAuthenticationSuccessHandler() {
        StorefrontAuthenticationSuccessHandler handler = new StorefrontAuthenticationSuccessHandler();
        handler.setCustomerService(storefrontAppConfig.customerService());
        return handler;
    }
}
