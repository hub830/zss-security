package com.zss.core;

import lombok.Data;

@Data
public class BrowserProperties {
  
  private String loginPage = "/zss-signin.html";
  
  private LoginType loginType = LoginType.JSON;
}
