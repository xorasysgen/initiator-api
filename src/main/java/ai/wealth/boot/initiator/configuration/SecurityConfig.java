package ai.wealth.boot.initiator.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	private static final String[] permitted_url = {
			"/users-endpoint/**/**",
			"/actuator/**",
			"/proxy/**",
			"/hystrix/**",
			"/v2/api-docs",
			"/configuration/ui",
			"/swagger-resources/**",
			"/configuration/**",
			"/swagger-ui.html",
			"/webjars/**",
			"/static/**",
			"/css/**",
			"/js/**",
			"/images/**"
    };
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(bCryptPasswordEncoder);
		
		
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/api/admin").hasAnyRole("ADMIN")
		.antMatchers("/api/info").hasAnyRole("USER", "ADMIN")
		.antMatchers(permitted_url).permitAll()
		.and().formLogin();
		http.csrf().disable();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	    return bCryptPasswordEncoder;
	}
}
