package com.zss.security.browser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import com.zss.core.SecurityProperties;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private SecurityProperties securityProperties;

  @Autowired
  private AuthenticationSuccessHandler zssAuthenticationSuccessHandler;

  @Autowired
  private AuthenticationFailureHandler zssAuthenticationFailureHandler;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }


  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.formLogin()//
        // http.httpBasic()//
        .successHandler(zssAuthenticationSuccessHandler)//
        .failureHandler(zssAuthenticationFailureHandler)//
        .loginPage("/authentication/require")//
        .loginProcessingUrl("/authentication/form")//
        .and()//
        .authorizeRequests()//
        .antMatchers(//
            "/authentication/require", //
            "/error", //
            securityProperties.getBrowser().getLoginPage()//
        )//
        .permitAll()//
        .anyRequest()//
        .authenticated()//
        .and()//
        .csrf()//
        .disable()//
    ;
  }

}
