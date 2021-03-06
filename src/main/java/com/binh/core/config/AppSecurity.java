//package com.binh.core.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//import com.binh.core.service.impl.BcommerceUserDetailService;
//
////@Configuration
////@EnableWebSecurity
//public class AppSecurity extends WebSecurityConfigurerAdapter {
//
//	@Autowired
//	private PasswordEncoder bCryptPasswordEncoder;
//
//	@Autowired
//	private BcommerceUserDetailService userDetailsService;
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//
////		http.authorizeRequests().
////		antMatchers("/login").permitAll()
////		.antMatchers("/api/**").permitAll()
////		.antMatchers("/").permitAll()
////				.antMatchers("/registration").permitAll()
////				.antMatchers("/admin/**").hasAuthority("ADMIN").anyRequest()
////				.authenticated().and().csrf().disable()
////				.formLogin().loginPage("/login")
////				.failureUrl("/login?error=true")
////				.defaultSuccessUrl(
////						"/administrator/dashboard", true).usernameParameter("user_name").passwordParameter("password").and()
////				.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login").and()
////				.exceptionHandling().accessDeniedPage("/access-denied");
//
////		http.csrf()
////        .disable()
////        .authorizeRequests()
////        .antMatchers("/index").permitAll()
////        .antMatchers("/registration").permitAll()
////        .antMatchers("/login").permitAll()
////
////        .and();
//
////		http.csrf().disable()
////		.authorizeRequests()
////		.antMatchers("/administrator/**").hasAuthority("ADMIN").anyRequest()
////		.authenticated().and();
////
////		super.configure(http);
////		http.authorizeRequests()
////                .antMatchers("/administrator").hasRole("admin")
////                .anyRequest().permitAll();
////		
////		http.headers().xssProtection();
////		super.configure(http);
////		http.authorizeRequests().antMatchers("/**").authenticated();
//	}
//
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
//	}
//}