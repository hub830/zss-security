package com.zss.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserQueryCondition {

//  @MyConstraint(message="����һ������")
  private String username;
  
  private int age;
  
  private int ageTo;
  
  private String xxx;
}
