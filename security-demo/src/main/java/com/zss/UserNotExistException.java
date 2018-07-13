package com.zss;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class UserNotExistException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private String id;

  
  public UserNotExistException() {
    super("�û�������");
  }


  public UserNotExistException(String id) {
    super("�û�������");
    this.id = id;
  }
  
  
  
}
