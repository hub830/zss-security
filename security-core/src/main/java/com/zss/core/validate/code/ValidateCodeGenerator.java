package com.zss.core.validate.code;

import javax.servlet.http.HttpServletRequest;

public interface ValidateCodeGenerator {

  ImageCode generator(HttpServletRequest request);
}
