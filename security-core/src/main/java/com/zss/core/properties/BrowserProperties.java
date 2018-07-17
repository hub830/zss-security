package com.zss.core.properties;

import lombok.Data;

@Data
public class BrowserProperties {

  private String loginPage = "/zss-signin.html";

  private LoginType loginType = LoginType.JSON;

  private int rememberMeSeconds = 36000;
}
