package pl.aleksanderkotbury.configuration;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableOAuth2Sso
public class AuthorizationConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/**").csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/students**", "/students/**", "/webjars**", "swagger-ui.html")
                .permitAll()
                .anyRequest()
                .authenticated();
    }
}
