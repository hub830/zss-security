package com.zss.security.browser.authentication;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSON;
import com.zss.core.properties.LoginType;
import com.zss.core.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
//public class ZssAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
  public class ZssAuthenticationSuccessHandler extends  SavedRequestAwareAuthenticationSuccessHandler {

  @Autowired 
  private SecurityProperties securityProperties;
  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {
    log.info("µÇÂ¼³É¹¦");
    if(LoginType.JSON.equals(securityProperties.getBrowser().getLoginType()))
    {
      response.setContentType("application/json;charset=UTF-8");
      response.getWriter().write(JSON.toJSONString(authentication));
      
    }
    super.onAuthenticationSuccess(request, response, authentication);
  }

}
