package com.zss.core;

import org.springframework.boot.context.properties.ConfigurationProperties;
import lombok.Data;

@Data
@ConfigurationProperties(prefix="zss.security")
public class SecurityProperties {
  private BrowserProperties browser = new BrowserProperties();;
}
