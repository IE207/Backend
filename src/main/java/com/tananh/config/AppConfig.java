package com.tananh.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class AppConfig {

	@Autowired OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler; 
	@Bean
	public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception{
		http.
			sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeHttpRequests(auth-> auth.requestMatchers("/api/**").authenticated()
					.anyRequest().permitAll())
			.addFilterBefore(new JwtValidator(), BasicAuthenticationFilter.class)
			.csrf().disable()
			.cors().configurationSource(CorsConfigurationSource()).and()
			.oauth2Login(oath2 -> {
                oath2.successHandler(oAuth2LoginSuccessHandler);
            }
					)
			.httpBasic().and().formLogin();
		
		return http.build();
	}

	private CorsConfigurationSource CorsConfigurationSource() {
		
		return new CorsConfigurationSource() {
			
			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				CorsConfiguration cfg = new CorsConfiguration();
				cfg.setAllowedOrigins(Arrays.asList("http://localhost:4200",
						"http://localhost:3000","http://localhost:5454","http://localhost:5454/ws"
						));
				cfg.setAllowedMethods(Collections.singletonList("*"));
				cfg.setAllowCredentials(true);
				cfg.setAllowedHeaders(Collections.singletonList("*"));
				cfg.setExposedHeaders(Arrays.asList("Authoriztion"));
				cfg.setMaxAge(3600L);
				return cfg;
			}
		};
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); 
	}
}
