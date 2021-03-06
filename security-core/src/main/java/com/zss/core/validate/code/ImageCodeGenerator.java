package com.zss.core.validate.code;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.ServletRequestUtils;
import com.zss.core.properties.ImageCodeProperties;
import com.zss.core.properties.SecurityProperties;

public class ImageCodeGenerator implements ValidateCodeGenerator {

  private SecurityProperties securityProperties;

  public void setSecurityProperties(SecurityProperties securityProperties) {
    this.securityProperties = securityProperties;
  }

  @Override
  public ImageCode generator(HttpServletRequest request) {
    ImageCodeProperties codeProperties = securityProperties.getCode().getImage();
    int width = ServletRequestUtils.getIntParameter(request, "width", codeProperties.getWidth());
    int height =
        ServletRequestUtils.getIntParameter(request, "height", codeProperties.getHeight());;
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    Graphics g = image.getGraphics();

    Random random = new Random();

    g.setColor(getRandColor(200, 250));
    g.fillRect(0, 0, width, height);
    g.setFont(new Font("Times New Roman", Font.ITALIC, 20));
    g.setColor(getRandColor(160, 200));
    for (int i = 0; i < 155; i++) {
      int x = random.nextInt(width);
      int y = random.nextInt(height);
      int xl = random.nextInt(12);
      int yl = random.nextInt(12);
      g.drawLine(x, y, x + xl, y + yl);
    }

    String sRand = "";
    for (int i = 0; i < codeProperties.getLength(); i++) {
      String rand = String.valueOf(random.nextInt(10));
      sRand += rand;
      g.setColor(
          new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
      g.drawString(rand, 13 * i + 6, 16);
    }

    g.dispose();

    return new ImageCode(image, sRand, codeProperties.getExpireIn());
  }

  /**
   * ���������������
   * 
   * @param fc
   * @param bc
   * @return
   */
  private Color getRandColor(int fc, int bc) {
    Random random = new Random();
    if (fc > 255) {
      fc = 255;
    }
    if (bc > 255) {
      bc = 255;
    }
    int r = fc + random.nextInt(bc - fc);
    int g = fc + random.nextInt(bc - fc);
    int b = fc + random.nextInt(bc - fc);
    return new Color(r, g, b);
  }
}
