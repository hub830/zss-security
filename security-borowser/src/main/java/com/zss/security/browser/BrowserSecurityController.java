package com.zss.security.browser;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.zss.core.SecurityProperties;
import com.zss.security.browser.support.SimpleResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class BrowserSecurityController {

  private RequestCache requestCache = new HttpSessionRequestCache();
  
  private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
  
  @Autowired
  private SecurityProperties securityProperties;
  /**
   * ����Ҫ�����֤ʱ��ת������
   * @param request
   * @param response
   * @return
   * @throws IOException 
   */
  @RequestMapping("authentication/require")
  @ResponseStatus(code=HttpStatus.UNAUTHORIZED)
  public SimpleResponse requireAuthentication(HttpServletRequest request,HttpServletResponse response) throws IOException
  {
    SavedRequest savedRequest= requestCache.getRequest(request, response);
    if(savedRequest!=null)
    {
      String targetUrl = savedRequest.getRedirectUrl();
      log.info("������ת��������{}",targetUrl);
      if(StringUtils.endsWithIgnoreCase(targetUrl, ".html"))
      {
        redirectStrategy.sendRedirect(request, response,securityProperties.getBrowser().getLoginPage());
      }
        
    }
    return new SimpleResponse("���ʵķ�����Ҫ�����֤���������û�����¼ҳ");
  }
}
