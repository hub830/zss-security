package com.zss;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class UserNotExistException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private String id;

  
  public UserNotExistException() {
    super("用户不存在");
  }


  public UserNotExistException(String id) {
    super("用户不存在");
    this.id = id;
  }
  
  
  
}
