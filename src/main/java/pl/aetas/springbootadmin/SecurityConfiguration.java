package pl.aetas.springbootadmin;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author gelder
 * @see "https://github.com/codecentric/spring-boot-admin/blob/master/spring-boot-admin-samples/spring-boot-admin-sample/src/main/java/de/codecentric/boot/admin/SpringBootAdminApplication.java"
 */
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		// Pa	ge with login form is served as /login.html and does a POST on /login
		http.formLogin()
			.loginPage("/login.html")
			.loginProcessingUrl("/login")
			.permitAll();
		// The UI does a POST on /logout on logout
		http.logout()
			.logoutUrl("/logout");
		// The ui currently doesn't support csrf
		http.csrf()
			.disable();

		// Requests for the login page and the static assets are allowed
		http.authorizeRequests()
			.antMatchers("/login.html", "/**/*.css", "/img/**", "/third-party/**")
			.permitAll();
		// ... and any other request needs to be authorized
		http.authorizeRequests()
			.antMatchers("/**")
			.authenticated();

		// Enable so that the clients can authenticate via HTTP basic for registering
		http.httpBasic();
	}
}
