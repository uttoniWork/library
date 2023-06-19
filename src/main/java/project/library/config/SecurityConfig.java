package project.library.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and() // Enable CORS configuration
                .csrf().disable() // Disable CSRF protection
                .headers().addHeaderWriter((request, response) -> {
                    response.setHeader("Access-Control-Allow-Origin", "http://localhost:16000");
                    response.setHeader("Access-Control-Allow-Methods", "POST, GET");
                    response.setHeader("Access-Control-Max-Age", "3600");
                    response.setHeader("Access-Control-Allow-Headers", "Content-Type");
                }).and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll() // Allow pre-flight requests
                .anyRequest().permitAll(); // Allow all other requests
    }
}