package com.cos.photogramstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// TODO : 시큐리티 최신버전
@Configuration // IoC
public class SecurityConfig {
	
    @Bean
    WebSecurityCustomizer ignore(){ // 정적자원 security filter에서 제외시키기
        return w -> w.ignoring().requestMatchers("/static/**", "/h2-console/**");
    }

	@Bean
	BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain configure(HttpSecurity http) throws Exception {
		
		http.csrf(c -> c.disable());

		http.authorizeHttpRequests(authorize -> authorize
				.requestMatchers("/", "/user/**", "/image/**", "/subscribe/**", "/comment/**", "/api/**")
				.authenticated().anyRequest().permitAll());

		http.formLogin(form -> form.loginPage("/auth/signin").loginProcessingUrl("/auth/signin").defaultSuccessUrl("/"));

		return http.build();
	}

}
 