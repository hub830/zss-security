package com.zss.security.browser;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import com.zss.core.properties.SecurityProperties;
import com.zss.core.validate.code.ValidateCodeFilter;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private SecurityProperties securityProperties;

  @Autowired
  private AuthenticationSuccessHandler zssAuthenticationSuccessHandler;

  @Autowired
  private AuthenticationFailureHandler zssAuthenticationFailureHandler;

  @Autowired
  private DataSource dataSource;

  @Autowired
  private UserDetailsService userDetailsService;

  @Bean
  public PersistentTokenRepository persistentTokenRepository() {
    JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
    tokenRepository.setDataSource(dataSource);
//    tokenRepository.setCreateTableOnStartup(true);
    return tokenRepository;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }


  @Override
  protected void configure(HttpSecurity http) throws Exception {
    ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
    validateCodeFilter.setZssAuthenticationFailureHandler(zssAuthenticationFailureHandler);
    validateCodeFilter.setSecurityProperties(securityProperties);
    validateCodeFilter.afterPropertiesSet();

    http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)//
        .formLogin()//
        // http.httpBasic()//
        .successHandler(zssAuthenticationSuccessHandler)//
        .failureHandler(zssAuthenticationFailureHandler)//
        .loginPage("/authentication/require")//
        .loginProcessingUrl("/authentication/form")//
        .and()//
        .rememberMe()//
        .tokenRepository(persistentTokenRepository())//
        .userDetailsService(userDetailsService)//
        .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
        .and()//
        .authorizeRequests()//
        .antMatchers(//
            "/authentication/require", //
            "/error", //
            "/code/image", //
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
