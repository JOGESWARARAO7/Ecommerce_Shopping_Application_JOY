package com.example.mainproject.joy.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors().and()
            .csrf().disable() // Disable CSRF for testing
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/signup/signUpData", "/signup/signUpOtpCheck", "/signup/loginCheck","/product/productadd","/product/getallproduct","/product/getproductbyid/{id}","/wishlist/addwhislist","/wishlist/user/{userId}","/wishlist/deletewishlist","/addBag/addToBag",
                		"/addBag/user/{userId}","/addBag/deleteToTheBag","/filter/**","/order/create-payment","/order/save-order","/adminSignupAndLogin/**","/product/productremove/{id}","/signup/fpotpgenarated/{email}","/signup/forgetOtpCheck","/signup/changepassword").permitAll()
                .anyRequest().authenticated()
            );

        return http.build();
    }



    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
