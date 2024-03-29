package com.twitter.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/")
                .hasAnyAuthority("ROLE_USER", "ROLE_ADMIN", "ROLE_READ_ONLY")
                .antMatchers("/index/**")
                .hasAnyAuthority("ROLE_USER", "ROLE_ADMIN", "ROLE_READ_ONLY")
                .anyRequest().permitAll()
                .and()
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/login-process")
                .failureUrl("/login?error")
                .defaultSuccessUrl("/index")
                .and()
                .logout().logoutSuccessUrl("/login");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                    .password(bCryptPasswordEncoder.encode("password"))
                    .roles("USER")
                .and()
                .withUser("admin")
                    .password(bCryptPasswordEncoder.encode("password"))
                    .roles("ADMIN")
                .and()
                .passwordEncoder(bCryptPasswordEncoder);
        auth.jdbcAuthentication()
                .usersByUsernameQuery("SELECT u.login, u.password, 1 from user u where u.login=?")
                .authoritiesByUsernameQuery("SELECT u.login, u.role, 1 from user u where u.login=?")
                .dataSource(jdbcTemplate.getDataSource())
                .passwordEncoder(bCryptPasswordEncoder);
    }
}
