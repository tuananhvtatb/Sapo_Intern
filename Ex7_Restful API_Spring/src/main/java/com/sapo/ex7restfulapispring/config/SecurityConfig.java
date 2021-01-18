package com.sapo.ex7restfulapispring.config;

import com.sapo.ex7restfulapispring.jwt.JwtAuthenticationFilter;
import com.sapo.ex7restfulapispring.jwt.JwtTokenProvider;
import com.sapo.ex7restfulapispring.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;

    private final JwtTokenProvider tokenProvider;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService, JwtTokenProvider tokenProvider) {
        this.userDetailsService = userDetailsService;
        this.tokenProvider = tokenProvider;
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(tokenProvider, userDetailsService);
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests() // thực hiện xác thực request ngưười dùng gửi lên.

                 /*không thực hiện xác thực đối với các url này.
                .antMatchers("/admin/login").permitAll()*/
            .antMatchers("/admin").permitAll();
//                // thực hiện xác thực với các url kiểu ..../admin/....
//                //.antMatchers(HttpMethod.POST).hasAnyAuthority("ADMIN")
//                //.antMatchers(HttpMethod.PUT).hasAnyAuthority("ADMIN")
//                .anyRequest().authenticated();

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
