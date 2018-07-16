package com.zss.security.browser.authentication;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSON;
import com.zss.core.LoginType;
import com.zss.core.SecurityProperties;
import com.zss.security.browser.support.SimpleResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ZssAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

  @Autowired
  private SecurityProperties securityProperties;

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException exception) throws IOException, ServletException {
    log.info("µÇÂ¼Ê§°Ü", exception);
    if (LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())) {

      response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
      response.setContentType("application/json;charset=UTF-8");
      response.getWriter().write(JSON.toJSONString(new SimpleResponse(exception.getMessage())));
      return;
    }
    super.onAuthenticationFailure(request, response, exception);
  }

}
