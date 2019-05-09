package com.tsyb.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigAdapter extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private UsersService usersService;
    @Value("${spring.queries.users-query}")
    private String usersQuery;
  //  @Value("${spring.queries.roles-query}")
  //  private String rolesQuery;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/trips/**").hasRole("boss")
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/").and().exceptionHandling()
                .accessDeniedPage("/access-denied");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.
                jdbcAuthentication()
               .dataSource(dataSource)
               .usersByUsernameQuery(usersQuery)
              //  .authoritiesByUsernameQuery(rolesQuery)
                .passwordEncoder(passwordEncoder());
           //    auth.authenticationProvider(authenticationProvider());
    }

    public BCryptPasswordEncoder passwordEncoder(){
       return new BCryptPasswordEncoder();
    }

   // public DaoAuthenticationProvider authenticationProvider(){
    //    DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
    //    auth.setUserDetailsService(usersService);
    //    auth.setPasswordEncoder(passwordEncoder());

    //    return auth;
   // }

}

