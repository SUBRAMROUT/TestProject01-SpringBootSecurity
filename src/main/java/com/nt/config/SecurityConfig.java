package com.nt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		// for Authentication info provider configuration
		/*auth.inMemoryAuthentication().withUser("raja").password("{noop}rani").roles("CUSTOMER");
		auth.inMemoryAuthentication().withUser("rajesh").password("{noop}hyd").roles("MANAGER");
		auth.inMemoryAuthentication().withUser("mahesh").password("{noop}delhi").roles("MANAGER","CUSTOMER");
		auth.inMemoryAuthentication().withUser("suresh").password("{noop}vizag").roles("VISITOR");
		*/
		
		
		auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("raja").password("$2a$10$CozfEd/7eRxYeACdm/jwjOAZrYMhlp9NEvWZw5W4beV7WKP8UbdDu").roles("CUSTOMER");
		auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("rajesh").password("$2a$10$5hp5A2TnN6GwhhMqUfoHYu0Y0a2CBiU6l58RJ6fGWpt3D5ngp5L3u").roles("MANAGER");
		auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("mahesh").password("$2a$10$M.efXQwU/Ur9b6EqK8vZg.iKFZ5NbUHsKpgRl6H8uB3IR3UnxkUs2").roles("MANAGER","CUSTOMER");
		auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("suresh").password("$2a$10$E3xHsTFXGivK2I1FbL.kZu7vv8nsAB9eHrQQdS3R9G8psBemOjy7K").roles("VISITOR");
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		// configure Authentication + Authorization on the urls
		http.authorizeHttpRequests().antMatchers("/").permitAll()
		.antMatchers("/offers").authenticated()
		.antMatchers("/balance").hasAnyRole("CUSTOMER","MANAGER")
		.antMatchers("/loanApprove").hasRole("MANAGER")
		.anyRequest().authenticated()
		.and().formLogin()  // for form based authentication
		.and().logout() //logout from the page
		//.and().httpBasic()   //enables basic Authentication(uses the browser generated dialog box for asking username.password)
		.and().exceptionHandling().accessDeniedPage("/denied")//for configuring custom page for authorization failure
		.and().sessionManagement().maximumSessions(2).maxSessionsPreventsLogin(true);
	}

}
