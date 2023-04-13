package in.nareshit.raghu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import in.nareshit.raghu.constants.UserRole;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity http)
			throws Exception {
		http.authorizeRequests()
		.antMatchers("/user/validateMail","/user/showForgot","/user/genNewPwd").permitAll()
		.antMatchers("/customer/register","/customer/save").permitAll()
		.antMatchers("/cart/add").permitAll()
		.antMatchers("/user/login").permitAll()
		.antMatchers("/search/**","/").permitAll()
		.antMatchers("/coupon/apply","/coupon/remove").hasAuthority(UserRole.CUSTOMER.name())
		.antMatchers("/user/register","/user/save").hasAuthority(UserRole.ADMIN.name())
		.antMatchers("/brand/**","/category/**","/categorytype/**").hasAuthority(UserRole.EMPLOYEE.name())
		.antMatchers("/product/**","/stock/**","/coupon/**","/shipping/**").hasAuthority(UserRole.SALES.name())
		.antMatchers("/order/reports").hasAuthority(UserRole.SALES.name())
		.anyRequest().authenticated()
		
		.and()
		.formLogin()
		.loginPage("/user/login") //to show login page
		.loginProcessingUrl("/login") // todo login check
		.defaultSuccessUrl("/user/setup", true) // on login success
		.failureUrl("/user/login?error=true") // on login failure
		
		.and()
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout")) //todo logout
		.logoutSuccessUrl("/user/login?logout=true") // on logout success
		;
		
	}
}
