package com.study.config;

import javax.sql.DataSource;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.study.handler.*;
import com.study.service.CustomUserDetailService;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private DataSource dataSource;
	
	public SecurityConfig(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	@Bean
	public CustomLoginSuccessHander loginSuccessHandler() {
		return new CustomLoginSuccessHander();
	}
	
	@Bean
	public CustomAccessDeniedHandler accessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailService();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 한글 인코딩
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");
		encodingFilter.setForceEncoding(true); // 기존 인코딩이 있어도 설정한 값 사용
		http.addFilterBefore(encodingFilter, CsrfFilter.class);
		
		http.formLogin().loginPage("/login").failureUrl("/login-error").successHandler(loginSuccessHandler());
		
		http.logout().invalidateHttpSession(true).logoutSuccessUrl("/").deleteCookies("remember-me");
		
		http.exceptionHandling(exception -> exception.accessDeniedHandler(accessDeniedHandler()));
		
		http.rememberMe().tokenRepository(persistentTokenRepository()).tokenValiditySeconds(604800);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
	}
	
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl jdbcTokenRepositoryImpl = new JdbcTokenRepositoryImpl();
		jdbcTokenRepositoryImpl.setDataSource(dataSource);
		return jdbcTokenRepositoryImpl;
	}
}