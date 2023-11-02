package com.eom.shoppingmall.shoppingmall.config;

import com.eom.shoppingmall.shoppingmall.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    MemberService memberService;

    @Autowired
    EncoderConfig encoderConfig;

    private static final String[] WHITE_LIST = {
            "/member/**",
            "/",
            "/item/**",
            "/images"
    };
    @Bean
    protected SecurityFilterChain config(HttpSecurity http) throws Exception {
        http
                .cors()
                    .and()
                .csrf().disable()
                .httpBasic().disable()
                .formLogin().disable()
                .formLogin()
                .loginPage("/member/login")
                .defaultSuccessUrl("/", true)
                .usernameParameter("email")
                .failureUrl("/member/login/error")
                    .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .logoutSuccessUrl("/")
                    .and()
                .authorizeHttpRequests()
                .requestMatchers(WHITE_LIST).permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint());
        return http.build();
    }

    public AuthenticationManager authenticationManager(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService).passwordEncoder(encoderConfig.bCryptPasswordEncoder());
        return auth.build();
    }
}
