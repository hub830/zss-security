package com.zss.core.validate.code;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import com.zss.core.properties.ImageCodeProperties;
import com.zss.core.properties.SecurityProperties;

@RestController
public class ValidateCodeController {

  public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

  private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

  @Autowired
  ValidateCodeGenerator imageCodeGenerator;

  @RequestMapping("code/image")
  public void createCode(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    ImageCode imageCode = imageCodeGenerator.generator(request);
    sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, imageCode);
    ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
  }


 
}
