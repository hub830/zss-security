package com.zss.security.browser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MyUserDetailsService implements UserDetailsService {

  @Autowired
  PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    log.info("-------�����û������� �û���Ϣ:{}", username);
    String password = passwordEncoder.encode("123456");
    log.info("---------���ݿ�������:{}", password);
    return new User(username, password,
        AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
  }

}
