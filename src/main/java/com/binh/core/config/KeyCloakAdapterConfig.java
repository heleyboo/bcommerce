package com.binh.core.config;

import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true)
@Order(101)
public class KeyCloakAdapterConfig extends KeycloakWebSecurityConfigurerAdapter {
	/**
	 * Registers the KeycloakAuthenticationProvider with the authentication manager.
	 */

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		KeycloakAuthenticationProvider keycloakAuthenticationProvider = keycloakAuthenticationProvider();
		keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
		auth.authenticationProvider(keycloakAuthenticationProvider);
	}

	/**
	 * Defines the session authentication strategy.
	 */
	@Bean
	@Override
	protected NullAuthenticatedSessionStrategy sessionAuthenticationStrategy() {
		return new NullAuthenticatedSessionStrategy();
	}

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		super.configure(http);
//		http.authorizeRequests().antMatchers("/api/*").hasRole("user").antMatchers("/admin*").hasRole("admin")
//				.anyRequest().permitAll();
//	}
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		super.configure(http);
//		http.authorizeRequests().anyRequest().permitAll();
//		http.csrf().disable();
//	}

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		super.configure(http);
//		http.authorizeRequests().antMatchers("/**").hasAnyRole("app-user").anyRequest().permitAll();
//	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		super.configure(http);
		http.csrf().disable()
				.authorizeRequests().antMatchers("/api/**").hasRole("user").anyRequest().authenticated();
	}
}
