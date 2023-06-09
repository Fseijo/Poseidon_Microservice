package com.fseijo.mswebapp.secutity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

      @Override
      protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.inMemoryAuthentication()
                    .withUser("springuser").password(passwordEncoder().encode("user123"))
                    .roles("USER")
                    .and()
                    .withUser("admin").password(passwordEncoder().encode("admin123"))
                    .roles("ADMIN");
      }


      @Override
      protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers("/", "home", "/css/*", "/js/*")
                    .permitAll()
                    .antMatchers("/admin").hasRole("ADMIN")
                    .antMatchers("/user").hasRole("USER")
                    .anyRequest()
                    .authenticated()
                    .and()
                    .formLogin();
      }
      @Bean
      public PasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
      }
}
