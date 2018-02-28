package de.michiruf.control_server.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Michael Ruf
 * @since 2016-04-07
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    // TODO We need this to disable basic auth?!

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.inMemoryAuthentication()
        //        .withUser("admin")
        //        .password("admin")
        //        .roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.authorizeRequests()
        //        .antMatchers("/**")
        //        .hasRole("USER")
        //        .and()
        //        .httpBasic();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //auth.inMemoryAuthentication()
        //        .withUser("user")
        //        .password("password")
        //        .roles("USER");
    }
}
