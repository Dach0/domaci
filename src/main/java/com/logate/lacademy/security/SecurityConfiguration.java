package com.logate.lacademy.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.logate.lacademy.security.components.UserDetailsComponent;
import com.logate.lacademy.security.exceptions.HttpAuthenticationPoint;
import com.logate.lacademy.security.jwt.JWTConfigurer;
import com.logate.lacademy.security.jwt.TokenProvider;

@Description(value = "Spring Security Configuration.")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private HttpAuthenticationPoint authenticationProvider;
	
	@Autowired
	private UserDetailsComponent userDetailsComponent;
	
	@Autowired
    private TokenProvider tokenProvider;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authBuilder) throws Exception 
    {
	    	authBuilder
	            .userDetailsService(userDetailsComponent)
	            .passwordEncoder(passwordEncoder());
    }


	@Override
	protected void configure(HttpSecurity http) throws Exception 
	{
		http
			.exceptionHandling().authenticationEntryPoint(authenticationProvider)
			.and()
			.csrf().disable()
			.headers().frameOptions().disable()
			.and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/api/employees/validator").permitAll()
            .antMatchers("/api/users/**").permitAll() // hasAnyRole("ADMIN", "DEVELOPER")
            .antMatchers("/api/employees/**").permitAll()
            .antMatchers("/auth/**").permitAll()
            .antMatchers(HttpMethod.GET, "/api/articles/comments").permitAll()
            .antMatchers("/api/articles/{id}/comments").permitAll()
            .antMatchers(HttpMethod.PUT, "/api/articles/{articleId}/comments/{id}").permitAll()
            .antMatchers(HttpMethod.DELETE, "/api/comments/{id}").permitAll()
            .antMatchers(HttpMethod.POST, "/api/articles").permitAll() //hasRole("DEVELOPER")
            .antMatchers(HttpMethod.PUT, "/api/articles/{id}").permitAll() //hasAnyRole("ADMIN", "DEVELOPER")
            .antMatchers(HttpMethod.DELETE, "/api/articles/{id}").permitAll() //hasRole("ADMIN")
            .antMatchers(HttpMethod.GET, "/api/articles").permitAll() //hasRole("USER")
            .antMatchers(HttpMethod.GET, "/api/articles/{id}").permitAll() //hasRole("USER") 
            .antMatchers("/api/file/upload/article/**").permitAll()
            .antMatchers("/api/file/upload/encoded/**").permitAll()
            .antMatchers("/api/file/download/**").permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .apply(securityConfigurerAdapter());
	}
	
	private JWTConfigurer securityConfigurerAdapter() {
        return new JWTConfigurer(tokenProvider);
    }
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
