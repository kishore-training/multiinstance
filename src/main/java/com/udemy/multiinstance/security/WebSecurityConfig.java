package com.udemy.multiinstance.security;

import com.udemy.multiinstance.model.types.UserTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource(name = "userService")
    private UserDetailsService userDetailsService;

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    @Autowired
    private LoginFailureHandler loginFailureHandler;

    //    @Autowired
//    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService);
//    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/home**").permitAll()
                .antMatchers("/static/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/register**").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/doctor/addUser**").hasAuthority(UserTypeEnum.HOSPITAL_ADMIN.name())
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailureHandler)
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();

        http
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

    }


    private SimpleAuthenticationFilter authenticationTokenFilterBean() throws Exception {
        SimpleAuthenticationFilter authenticationFilter = new SimpleAuthenticationFilter();
        authenticationFilter.setAuthenticationManager(authenticationManagerBean());
        authenticationFilter.setAuthenticationSuccessHandler(loginSuccessHandler);
        authenticationFilter.setAuthenticationFailureHandler(loginFailureHandler);
        return authenticationFilter;
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }


    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }
}