package com.barberia.app.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//        http.authorizeRequests().antMatchers("/","/admin/login","/admin/logout").permitAll();
//        http.authorizeRequests().antMatchers("/admin").access("hasAnyRole('ROLE_ADMIN','ROLE_STAFF','ROLE_MANAGER')");
//        // Trang chỉ dành cho ADMIN
//        http.authorizeRequests().antMatchers("/admin").access("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')");
//        // Trang chỉ dành cho ADMIN
//        http.authorizeRequests().antMatchers("/admin").access("hasRole('ROLE_ADMIN')");

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/","/admin/css/**","/admin/assets/**","/admin/contactform/**","/admin/fonts/**","/admin/img/**","/admin/js/**","/admin/myjs/**","/admin/form-validation.css").permitAll()
                .antMatchers("/admin/**").authenticated()
                .antMatchers("/admin/salon/**").hasRole("ADMIN")
                .antMatchers("/admin/employees/**").hasAnyRole("ADMIN","MANAGER")

                .and()
                .formLogin()
                    .loginPage("/admin/login")
                    .permitAll()
                    .defaultSuccessUrl("/admin")
                    .usernameParameter("email")
                    .passwordParameter("password")
                .and()
                .logout()
                    .logoutUrl("/admin/logout")
//                    .deleteCookies("JSESSIONID")
                    .logoutSuccessUrl("/admin/login");
        http.authorizeRequests().and() //
                .rememberMe().tokenRepository(this.persistentTokenRepository()) //
                .tokenValiditySeconds(1 * 24 * 60 * 60); // 24h
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        InMemoryTokenRepositoryImpl memory = new InMemoryTokenRepositoryImpl(); // Ta lưu tạm remember me trong memory (RAM). Nếu cần mình có thể lưu trong database
        return memory;
    }
}
