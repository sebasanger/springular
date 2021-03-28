package com.sanger.springular.security;

import com.sanger.springular.jwt.JwtAuthorizationFilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final UserDetailsService userDetailsService;
	private final PasswordEncoder passwordEncoder;
	private final JwtAuthorizationFilter jwtAuthorizationFilter;
	private final AuthenticationEntryPoint authenticationEntryPoint;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().exceptionHandling()//
				.authenticationEntryPoint(authenticationEntryPoint).and().authorizeRequests()//
				.antMatchers("/auth/login").permitAll()//
				.antMatchers("/actuator/**").permitAll()//
				.antMatchers("/auth/logout").permitAll()//
				.antMatchers("/auth/refresh/token").permitAll()//
				.antMatchers("/reset-password/**").permitAll()//
				.antMatchers("/user/**").hasRole("ADMIN")//
				.antMatchers("/user/changePassword").permitAll()//
				.antMatchers("/auth/validate-acount").permitAll()//
				.antMatchers(HttpMethod.GET, "/files/**").permitAll()//
				.anyRequest().authenticated();

		// filtro
		http.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
	}

}
