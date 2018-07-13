package com.zss.web;

import java.util.Map;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import com.zss.UserNotExistException;

@ResponseBody
@ControllerAdvice
public class ControllerExceptionHandler extends DefaultErrorAttributes {


  @ExceptionHandler(UserNotExistException.class)
  // @ExceptionHandler(value = { UserNotExistException.class })
  public final ResponseEntity<?> handleUserNotExistException(UserNotExistException ex,
      WebRequest request) {
    Map<String, Object> attributes = getErrorAttributes(request, HttpStatus.UNAUTHORIZED);
    attributes.put("id", ex.getId());
    return handleExceptionInternal(attributes);

  }


  private Map<String, Object> getErrorAttributes(WebRequest request, HttpStatus status) {
    request.setAttribute("javax.servlet.error.status_code", status.value(),
        RequestAttributes.SCOPE_REQUEST);
    return super.getErrorAttributes(request, false);
  }


  private ResponseEntity<Object> handleExceptionInternal(Map<String, Object> body) {
    Integer status = (Integer) body.get("status");

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
    return new ResponseEntity<>(body, headers, HttpStatus.valueOf(status));
  }
}
